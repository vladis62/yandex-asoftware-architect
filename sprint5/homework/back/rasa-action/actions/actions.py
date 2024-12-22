from typing import Text, Dict, Any, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher

class ActionMonolithInfo(Action):

    def name(self) -> Text: return "action_monolith_info"

    async def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        dispatcher.utter_message(text="Что ты хочешь узнать монолитной архитектуре ?")
        return []

# action_microservice_info
class ActionMicroserviceInfo(Action):

    def name(self) -> Text: return "action_microservice_info"

    async def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        dispatcher.utter_message(text="Что ты хочешь узнать о микросервисах?")
        return []
