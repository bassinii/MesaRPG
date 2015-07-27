# language: pt
Funcionalidade: Usuario Crud Hibernate

    Cenario: Salvar Um Usuario
    Dado que eu possuo um usuario
    Quando eu recebo as informacoes do usuario: 1, 'maneco', 'ze', 'senha', 'zemaneco@gmail.com'
    Entao eu salvo o usuario
    
    Cenario: Excluir Um Usuario
    Dado que eu possuo um usuario
    Quando eu recebo as informacoes do usuario: 1, 'maneco', 'ze', 'senha', 'zemaneco@gmail.com'
    Entao eu excluo o usuario
    
    Cenario: Atualizar Um Usuario
    Dado que eu possuo um usuario
    Quando eu recebo as informacoes do usuario: 1, 'maneco', 'ze', 'senha', 'zemaneco@gmail.com'
    Entao eu atualizo o usuario
    
    Cenario: Buscar Um Usuario Por Login
    Dado que eu possuo um usuario
    Quando eu recebo as informacoes do usuario: 1, 'maneco', 'ze', 'senha', 'zemaneco@gmail.com'
    Entao eu busco o usuario

    Cenario: Buscar Um Usuario Por Login
    Dado que eu possuo um usuario
    Quando eu recebo as informacoes do usuario: 1, 'maneco', 'ze', 'senha', 'zemaneco@gmail.com'
    Entao eu busco o usuario por login
    
    Cenario: Buscar Um Usuario Por Login e Senha
    Dado que eu possuo um usuario
    Quando eu recebo as informacoes do usuario: 1, 'maneco', 'ze', 'senha', 'zemaneco@gmail.com'
    Entao eu busco o usuario por login e senha

    Cenario: Buscar Um Usuario Por Login e Senha
    Dado que eu quero listar os usuarios    
    Entao eu listo os usuario cadastrados