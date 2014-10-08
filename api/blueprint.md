FORMAT: 1A
HOST: http://mural.ufg.br

# Central UFG (REST API)
Central UFG, ou simplesmente Central, é um dos principais componentes do Projeto Mural UFG. 
A função é receber requisições de "divulgação de informações" e executá-las. 
A interação com a Central ocorre exclusivamente via software, ou seja, 
tanto quem faz requisição quanto quem recebe informação é software. 

# Raiz da Frase do Dia [/fraseDoDia]
API para frase do dia

## Obter frase do dia [GET]

+ Response 200 (application/json)
    + Body

            {
                "frase": "lorem ipsum"
            }

# Raiz da Token [/token]
API para obter nova token

## Obter uma nova token [GET]

+ Response 200 (application/json)
    + Body

            {
                "token": "01234567-89ab-cdef-0123-456789abcdef"
            }