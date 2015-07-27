# language: pt
Funcionalidade: Aventura Crud Hibernate

  Cenario: Salvando Uma Aventura
  Dado que eu tenho uma aventura 
  Quando eu tenho o nome da aventura 'Aventura 1' e pontuacao minima 2 e maxima 4 e descricao de 'Descricao Aventura 1'
  Entao a aventura deve ser salva


  Cenario: Consultando Uma Aventura
  Dado que eu uma aventura de codigo igual 1
  Quando eu consulto uma a aventura pelo codigo
  Entao eu devo ter os dados de uma aventura


  Cenario: Excluindo Uma Aventura
  Dado que eu tenho uma aventura
  Quando eu excluo uma aventura que possui codigo igual a 1
  Entao a aventura deve ser apagada

  Cenario: Consultando uma lista de Aventuras
  Dado que eu quero uma lista das aventuras
  Quando eu consulto todas as aventuras
  Entao eu consigo uma lista das aventuras

  Cenario: Atualizadno Uma Aventura
  Dado que eu tenho uma aventura
  Quando eu tenho todos os dados da aventura: 1, 'Aventura 1', 2, 4, 'Descricao Aventura 1'
  Entao a aventura deve ser atualizada

  Cenario: Listar Aventuras por Usuario
  Dado que eu tenho um Usuario de codigo 1
  Quando eu listo as aventuras por usuario
  Entao eu recebo uma lista de aventuras desse usuario
    


