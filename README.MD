Projeto: Sistema de Notificações

Objetivo:
Construir um sistema de notificações onde os usuários podem se inscrever para receber notificações sobre diferentes tipos de eventos.

o código inclui os padrões Singleton, Facade e Strategy. O NotificationService é o Singleton que mantém a lista de observadores e a
estratégia de notificação. A NotificationFacade atua para simplificar o uso do sistema de notificações. Os usuários (User) 
são observadores que recebem notificações. As estratégias de notificação (NotificationStrategy) definem como as notificações são enviadas.
