#Desafio AMcom - Fullstack Java

###Durante esse Desafio, através do teste técnico você será avaliado nos seguintes requisitos:

- Conhecimento Linguagem Programação Java, HTML, CSS;
- Princípios S.O.L.I.D e Clean Code;
- Habilidade de escrever testes de unidade;
- Capacidade de identificar e resolver problemas;
- Refatoração de código;
- Documentação de código;

Para atender o nosso cliente precisamos resolver alguns problemas e desenvolver novas funcionalidades na aplicação atual.
O aplicativo é uma WebAPI/MVC desenvolvida em Java 11 + Java EE 8 + JBoss EAP 7.2 + Banco Relacional H2 e estamos com problemas para identificar falhas na API e ao salvar as temperaturas, erros estão acontecendo. Além disso, só possuímos uma tela para consultar as temperaturas salvas no banco, mas precisamos de um CRUDL completo.

Precisamos:

1)	Retornar a lista de países como origem o arquivo ‘países.json’ e disponibilizar as informações em um endpoint.
   http://127.0.0.1:8080/desafio-web-1.0.0-SNAPSHOT/api/paises

2)	Verificar o Controller de Temperaturas e verificar itens para serem melhorados e consertados.
3)	Cálculos de temperaturas já realizados, não precisam ser calculados novamente em novas requisições, devem ser armazenados (cache) e retornados quando requisitado.
4)	Crie um novo endpoint para retornar temperaturas que estejam dentro de uma terminada faixa (padrão entre 18 e 25 graus Celsius) informada pelo consumidor e na escala informada: Celsius, Fahrenheit e Kelvin (se não for informada, utilizar Celsius).
5)	Retornar dados da API https://reqres.in/api/users?page=2 e aplicar filtros para buscar pessoas por email e/ou nome.
6)	Documente os endpoints no OpenApi/Swagger.
7)  Transforme a tela de consultar temperaturas registradas no banco em um CRUDL completo.
8)  Crie a tela de CRUDL de países.
9)  Aplique o tema "Vela" do Primefaces.
10)	Publique seu código em um repositório git privado e nos dê acesso de leitura 😊.
11)	Crie uma imagem Docker do seu aplicativo e publique lá no Docker Hub.

Diferencial:

- Persistir dados no Oracle XE (necessário incluir no README o passo a passo);
- Tornar a aplicação tolerante a falhas;
- Tornar a aplicação observável;

####Quanto tempo esperamos que você gaste para escrever esse teste?
Até 6 horas! Preferimos que você use 6 horas para propor uma implementação aproximada e descrever como continuar. Se você quiser dedicar mais tempo, tudo bem. Tente equilibrar os dois mundos.

####Preciso realizar todas as tarefas?
Não é obrigatório, faça até onde conseguir e tente mostrar o seu conhecimento no código, e justifique o porquê utilizou componentes e ferramentas.
