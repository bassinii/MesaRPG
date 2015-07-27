# language: pt
Funcionalidade: Fala Crud Hibernate

    Cenario: Salvar Uma Fala
    Dado que eu possuo uma Fala
    Quando eu recebo as informacoes da fala: 'autor', 'fala', 'd6-6', 1
    Entao eu salvo a fala

    Cenario: Consultar Lista de Falas
    Dado que eu quero listar as falas
    Quando eu consulto as falas
    Entao eu consigo uma lista de falas

    Cenario: Consultar Lista de Falas por Aventura
    Dado que eu quero listar as falas em uma Aventura de codigo 1
    Quando eu consulto as falas por aventura
    Entao eu consigo uma lista de falas por aventura

    Cenario: Consultar Fala pelo Codigo
    Dado que eu quero consultar uma fala de codigo 1
    Quando eu consulta a fala
    Entao eu obtenho a fala

    Cenario: Excluir Fala 
    Dado que eu possuo uma fala de codigo 1
    Quando eu excluir a fala
    Entao a fala deve ser apagada
    
    Cenario: Atualizar Uma Fala
    Dado que eu possuo uma Fala
    Quando eu possuo as informacoes da fala: 1, 'autor', 'fala', 'd6-6', 1
    Entao eu atualizado a fala

    