import express from 'express';
import fetch from 'node-fetch';
import { createClient } from 'redis';

const redis = await createClient()
    .on('error', err => console.log('Redis Client Error', err))
    .connect();

// извлечение данных
async function getCountries() {

    const response = await fetch(`https://restcountries.com/v3.1/all?fields=name,flags`);

    if (!response.ok) {
        throw new Error(response.statusText);
    }

    return await response.json();
}

const app = express();
app.get('/countries', async (req, res) => {
    try {
        // попробуем извлечь данные о странах из кеша
        let countries = null;
        try {
            // попробуем извлечь данные о странах из кеша
            countries = await redis.get('data');
        } catch (err) {
            console.log(err);
        }
        res.status(200).send(JSON.stringify(countries));
    } catch (err) {
        console.log(err);
        res.sendStatus(500);
    }
});

setInterval(async () => {
    console.log('Running a scheduled task');
    let countries = await getCountries();
    updateData('data', JSON.stringify(countries));
}, 12000); //every 2 seconds

// определение функции для обновления данных,
// определение приоритетности обновлений кеша
// и асинхронной отсрочки обновлений хранилища данных
async function updateData(key, newData) {
    // инициируются обновления кеша
    await redis.set(key, JSON.stringify(newData));
    console.log('cache updated');
}

const port = 3000;
app.listen(port, () => {
    console.log(`Server listening on http://localhost:${port}`);
});
 