# Запуск бота

### Установка Conda:
1) mkdir -p ~/miniconda3
2) curl https://repo.anaconda.com/miniconda/Miniconda3-latest-MacOSX-arm64.sh -o ~/miniconda3/miniconda.sh
3) bash ~/miniconda3/miniconda.sh -b -u -p ~/miniconda3
4) rm ~/miniconda3/miniconda.sh
5) source ~/miniconda3/bin/activate
6) conda init --all

### Создаем ENV

1) conda create --name myenv
2) conda activate myenv
3) conda install python=3.9.12

### Установка Rasa:
1) pip install rasa
2) pip install transformers

### Тренировка модели и запуск чата:
1) rasa train (если меняли что-то в конфигах)
2) rasa run --enable-api
3) rasa run --enable-api --debug
4) rasa run --enable-api --debug --cors "*"

Логи чата находятся в файле sprint5/homework/rasa.txt
