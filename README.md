# api-cadastro-loja

Em 2019 a Wine inaugurou a sua primeira loja física em Belo Horizonte com uma proposta
omnichannel . ​ Nessa loja, além da compra presencial, o cliente pode realizar a compra pelo
App da Wine, que passou a fornecer um catálogo específico daquela loja física.

O fluxo de compra:
1. cliente abre o App;
2. caso esteja em um ​ raio de atendimento da loja física​ (ex: 5km, 10km), o catálogo
da loja física fica disponível para compra;
3. cliente realiza a compra e recebe o vinho dentro de alguns minutos;

Em 2020 foram abertas 7 novas lojas, 3 delas em São Paulo (Pinheiros, Jardins e Moema).
Com isso, alguns raios de atendimentos das lojas ficaram sobrepostos, gerando assim
alguns "conflitos" de região de atendimento de cada unidade.

Com o objetivo de atender melhor esses clientes oferecendo ​ apenas uma loja física por
CEP de entrega​ , precisa-se criar um serviço (API) para realizar as seguintes atividades:

API REST para cadastrar as faixas de CEP de cada loja;
API REST para retornar qual é a loja física que atende determinado CEP;

REGRAS
● A tabela de cadastro de CEP deve possuir as seguintes colunas: ID,
CODIGO_LOJA, FAIXA_INICIO e FAIXA_FIM;
● As faixas de CEP não pode conflitar com as de outras lojas;
● Deve ser possível editar/excluir uma faixa de CEP;
● A aplicação deve ser feita em ​ Springboot​ + ​ REST​ + JPA/Hibernate

base_url () -> http://localhost:8080/cadastro

https://api-cadastro-cep-lojas.herokuapp.com/cadastro

POST/\
GET/\
GET/{id}\
PUT/{id}\
GET/cep/{cep}\
GET/codigo/{codigo}\
DELETE/{id}\
