# [DEPRECATED] use [chatgpt-spring-boot-starter](https://github.com/Sprytin/chatgpt-spring-boot-starter)
# Kotlin Spring ChatGPT REST API 
**Kotlin Spring REST API** version of reverse engineered ChatGPT API https://github.com/acheong08/ChatGPT proxy

You can use multiple tokens, and the load between them will be balanced.
## Technologies Used
- Kotlin 1.7.22
- Spring Boot 3.0.3
## Installation
1. clone 
```shell
git clone https://github.com/Sprytin/ChatGPT.git
```
2. add your tokens
```yaml
bridge:
  #Cloudflare bypass proxy url
  proxyUrl: https://chatgpt.duti.tech/api/conversation  
  #https://chat.openai.com/api/auth/session chatGPT access token
  tokens:
    - "<TOKEN>"
    - "<SECOND_TOKEN>"
    - ...
```
3. run
```shell
./gradlew bootRun
```

## How to use

```shell
curl -X POST http://localhost:8080/api/v1/ask \
  -H 'Content-Type: application/json' \
  -d '{"message":"Hello, how are you?"}'
```
### Stream endpoint
```shell
curl -X POST http://localhost:8080/api/v1/ask/stream \
  -H 'Content-Type: application/json' \
  -d '{"message":"Hello, how are you?"}'
```

### With conversationId and parentMessageId
```shell
curl -X POST http://localhost:8080/api/v1/ask \
  -H 'Content-Type: application/json' \
  -d '{"message": "What is your name?", "conversationId": "<conversation_id>", "parentMessageId": "<parent_message_id>"}'
```

## Answer
```json
{
    "answer": "My name is ChatGPT.",
    "messageId": "ec5d559b-7085-4b4d-adb1-0e4e3d07d42c",
    "conversationId": "0d6cf11e-5c77-41b4-b732-0f7a537fff2c"
}
```
## TODO()
Error Handling
