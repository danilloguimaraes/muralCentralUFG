FORMAT: 1A
HOST: http://mural.ufg.br

# Central UFG (REST API)
Central UFG, ou simplesmente Central, é um dos principais componentes do Projeto Mural UFG. 
A função é receber requisições de "divulgação de informações" e executá-las. Em alguns
casos a execução não é executada imediatamente, mas agendada para o instante desejado.
A interação com a Central ocorre exclusivamente via software, ou seja, 
tanto quem faz requisição quanto quem recebe informação é software. 

# Cardápio [/cardapio]
## Lista de restaurantes [/cardapio/restaurantes]
Adiciona, recupera, busca e remove restaurantes "próximos" aos campi da UFG.

+ Request (application/json)

+ Response 200 (application/json)
    + Body

            {
                "restaurantes": 
                [
                    {
                        "nome" : "Restaurante Universitário",
                        "id" : "7ecc7007-42bd-4e9a-b819-4382018eb00e"
                    },
                    
                    {
                        "nome" : "Churrascaria Boi na Brasa",
                        "id" : "bbfbe012-b1ca-40c3-9bad-d48118e7ab8e"
                    }
                ]
            }
            
# Frase do dia [/frase]
Adiciona, recupera, busca e remove a frase ou mensagem em um dado dia.

## Obter frase do dia [GET]
Recupera uma frase, possivelmente associada ao dia em questão, ou procura
por frase.

+ Request (application/json)

+ Response 200 (application/json)
    + Body

            {
                "frase": "Sem autor, sem data."
            }

+ Request (application/json)
    
    + Headers

            Accept: application/json

    + Body
    
            {
                "busca": "mais rara",
            }

+ Response 200 (application/json)

    + Body
    
            { 
                "frases": [
                    {
                        "frase": "Viver é a coisa mais rara do mundo. A maioria das pessoas apenas existe.",
                        "autor" : "Oscar Wilde"
                        "id" : "f0ecfb49-3406-4d44-b294-c89896bd29aa"
                    }
                ]
            }

## Acrescentar frase a um dia [PUT]
Insere uma frase possivelmente associada a um dado dia.
+ Request (application/json)
    
    + Headers

            Accept: application/json
            User: username
            Password: password
            
    + Body
    
            {
                "frase": "Viver é a coisa mais rara do mundo. A maioria das pessoas apenas existe.",
                "autor" : "Oscar Wilde"
            }

+ Response 200

## Remove frase do dia [DELETE]
Remove a frase associada ao identificador fornecido.
+ Request (application/json)
    
    + Headers

            Accept: application/json
            User: username
            Password: password
            
    + Body
    
            {
                "apagar": "f0ecfb49-3406-4d44-b294-c89896bd29aa"
            }

+ Response 200

# Token [/token]
Obtém nova token e recupera listagem de hashes de uma dada token.

## Obter uma nova token [GET]
Obtém nova token única.

+ Request (application/json)

+ Response 200 (application/json)
    + Body

            {
                "token": "01234567-89ab-cdef-0123-456789abcdef"
            }

# Envio [/envio]
Envia e recupera arquivos e hashes de arquivos associados à tokens

## Enviar novo arquivo [POST]
Envia um novo arquivo e o associa a uma token.

+ Request (application/json)
    
    + Headers

            Accept: application/json
            User: username
            Password: password
            
    + Body
    
            {
                "token": "01234567-89ab-cdef-0123-456789abcdef",
                "envio" : "I9IiIgdmVuZG9yc3ViPSIiLz4KCQkJPHVzZXJhZ2VudCBkZXNjcmlwdGlv"
            }

+ Response 200

## Remover os envios de uma token [DELETE]
Remove a lista de hashes SHA512 dos envios associados a uma token.

+ Request (application/json)

    + Headers
        
            Accept: application/json
            User: username
            Password: password

    + Body

            {
                "token": "01234567-89ab-cdef-0123-456789abcdef",
                "hashes": {
                    "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a",
                    "40b244112641dd78dd4f93b6c9190dd46e0099194d5a44257b7efad6ef9ff4683da1eda0244448cb343aa688f5d3efd7314dafe580ac0bcbf115aeca9e8dc114"
                }
            }
            
+ Response 200 (application/json)

## Envios [/envio/arquivo/{token}/{hash}]
Recupera a lista de hashes SHA512 dos envios associados a uma token.

+ Parameters

    + token (string) ... Um token único composto de hexadecimais agrupados em 8-4-4-4-12
    + hash (optional, string) ... Uma hash especificando qual envio deverá ser baixado
    
### Obter os envios de uma token [GET]

+ Request (application/json)

    + Headers
        
            Accept: application/json
            User: username
            Password: password

+ Response 200 (application/json)

    + Body

            {
                "envios": {
                    "I9IiIgdmVuZG9yc3ViPSIiLz4KCQkJPHVzZXJhZ2VudCBkZXNjcmlwdGlv"
                }
            }
            
## Envios [/envio/hash/{token}]
Recupera a lista de hashes SHA512 dos envios associados a uma token.

+ Parameters

    + token (string) ... Um token único composto de hexadecimais agrupados em 8-4-4-4-12
    
### Obter os envios de uma token [GET]

+ Request (application/json)

    + Headers
        
            Accept: application/json
            User: username
            Password: password

+ Response 200 (application/json)

    + Body

            {
                "hashes": {
                    "4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a",
                    "40b244112641dd78dd4f93b6c9190dd46e0099194d5a44257b7efad6ef9ff4683da1eda0244448cb343aa688f5d3efd7314dafe580ac0bcbf115aeca9e8dc114",
                    "3bafbf08882a2d10133093a1b8433f50563b93c14acd05b79028eb1d12799027241450980651994501423a66c276ae26c43b739bc65c4e16b10c3af6c202aebb"
                }
            }

# Registro de Alerta [/alerta]

## Inserir novo alerta [POST]
Insere um novo alerta na Central UFG. Os dados são específicos para cada ação.

+ Request (application/json)
    
    + Headers

            Accept: application/json
            User: username
            Password: password
            
    + Body
    
            {
                "acao": "Aviso de Devolucao",
                "usuario": 1
                "dados": {
                    "codigo_obra": "abcd123",
                    "data_limite": "2014-10-07T20:00:00-03:00"
                },
                "data": "2014-10-04T19:42:56-03:00"
            }

+ Response 200
