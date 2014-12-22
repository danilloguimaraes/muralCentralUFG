muralufg-gcm
============

Api de comunicação pra envio de mensagem via GCM

Versão do appengine:
* **SDK 1.9.15** - [Instalação Google Plugin para o Eclipse](https://cloud.google.com/appengine/docs/java/tools/eclipse)

Configure os seguintes arquivos:
* [appengine-web.xml](../master/war/WEB-INF/appengine-web.xml), crie um projeto no https://console.developers.google.com e adicione o 
```xml
<application> ID DO PROJETO </application>
```
* [app.properties](../master/src/app.properties)
```php
# Sender ID
api_key= // Crendencial pública
# dado de envio da mensagem
data_key= // uma chave que será enviada com a mensagem e que o dispositivo que recebe conheça.
```

---

**POST /mensagem**
======================
> https://----.appspot.com/_ah/api/muralufggcm/v1/mensagem

*Requisição*
```
Content-Type:  application/json
{
 "regIds": array["string"],
 "tempoDeVida": integer,
 "texto": string
}
```
*Resposta*
```
{
 "id": integer,
 "sucesso": boolean,
 "falha": boolean,
 "falhas": array[
  {
   "status": string,
   "regId": string
  }
 ]
}
```
**GET /serverstatus**
======================
> https://----.appspot.com/_ah/api/muralufggcm/v1/serverstatus

*Resposta*
```
{
 "mensagem": string,
 "funcionando": boolean
}
```


