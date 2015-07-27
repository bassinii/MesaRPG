# language: pt
Funcionalidade: Ficha Crud Hibernate

	Cenario: Salvar Ficha
		Dado que eu tenho uma ficha
                Quando eu recebo as informacoes da ficha: 'Fulano'
		Entao eu devo salvar a ficha

         Cenario: Listar Fichas
                Dado que eu quero listar as fichas
                Entao eu recebo uma lista de fichas

        
        Cenario: Listar Fichas por Usuario
                Dado que eu tenho um usuario de codigo 1
                Quando eu quero listar as fichas por usuario
                Entao eu recebo uma lista de fichas por usuario
        
        
        Cenario: Buscar Ficha Por Codigo
                Dado que eu tenho uma ficha de codigo 1
                Quando eu busco a ficha pelo codigo
                Entao eu recebo uma ficha

        Cenario: Excluir Uma Ficha
                Dado que eu tenho uma ficha de codigo 1
                Quando eu excluo a ficha
                Entao a ficha e apagada
        
        Cenario: Atualizar Uma Ficha
                Dado que eu tenho uma ficha
                Quando eu recebo as novas informacoes da ficha: 1, 'Fulano'
                Entao eu atualizo a ficha        
                