# language: pt
Funcionalidade: Fala Crud Hibernate

    Cenario: Salvar Um Personagem
    Dado que eu possuo um Persoangem
    Quando eu recebo as informacoes do personagem: 1, 1, 1, 1
    Entao eu salvo o personagem

    Cenario: Excluir Um Personagem
    Dado que eu possuo um Persoangem
    Quando eu recebo as informacoes do personagem: 1, 1, 1, 1
    Entao eu excluo o personagem

    Cenario: Atualizar Um Personagem
    Dado que eu possuo um Persoangem
    Quando eu recebo as informacoes do personagem: 1, 1, 1, 1
    Entao eu atualizo o personagem

    Cenario: Buscar Um Personagem
    Dado que eu possuo um Persoangem
    Quando eu recebo as informacoes do personagem: 1, 1, 1, 1
    Entao eu busco o personagem
    
    Cenario: Listar Um Personagem
    Dado quero listar os personagens
    Entao eu listo todos os personagens cadastrados

    Cenario: Listar Personagem Por Usuario
    Dado o usuario de codigo 1
    Entao eu listo todos os personagens do usuario
    
    Cenario: Listar Personagem Por Aventura
    Dado a aventura de codigo 1
    Entao eu listo todos os personagens da aventura

    Cenario: Listar Personagem Por Aventura
    Dado a aventura de codigo 1
    Entao eu listo todos os personagens da aventura com ficha
    