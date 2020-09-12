# AmazonEvent
***Importante --> Descompactar a as pastas .idea e .mvn***

## O que já foi implementado:

(Itaciane)

-**Pré entrega**

 - Criação de evento
 - Eventos na página principal
 - Participação em evento
 - Listagem de participantes
 - Listagem de eventos
 
(Marcelo) 

-**Pré entrega**

 - Responsividade 
 - Carroussel 
 - Impressão de lista de participantes em PDF
 
-**Pós entrega**

 - Impressão de lista de presença em PDF
 - Gerenciamento de eventos
 - Pesquisa por titulo (pagina principal)
 - Pesquisa por titulo (TodosEventos - Moderador)
 - Pesquisa de participantes
 - Pesquisa de usuarios
 - CRUD de evento
 - Login e cadastro (spring security)
 - Páginas do moderador
 - Código já reduzido com o fragment (o que deu pra fazer ate momento)

## O que falta ajeitar:
 - Imprimir lista de presença e lista de participantes --> ***passar tudo para html e js***
 
 - Ajeitar o rodapé
   - deixar o rodapé na parte de baixo mesmo se tiver espaço no meio da tela
{ 
 - O participante deve ter o id de quem o cadastrou - para evitar flood **(precisa do id do usuario)**
 
 - Os eventos na página "meus eventos" devem aparecer apenas para o seu 'autor'**(precisa do id do usuario)**
 }
 - A pesquisa pode sobreescrever a paginação mas não o contrário
 
## O que falta implementar:    
 - Menssagens de erro
   - Login
   - Cadastro
   - Criação de evento
   - Inscrição
   - Evento não encontrado(pesquisa)
   - Participante não encontrado(pesquisa)
   - Usuario não encontrado(pesquisa)
   
 - Uma pesquisa para os eventos de cada organizador **(precisa do id do usuario)**
 
 - Emissão de certificado --> ***passar tudo para html e js***
   - Enviar certificado por email
 
 - Acessar e editar o perfil do usuário na página "Minha conta" **(precisa do id do usuario)**
   - Falta ter ideia de como faz
   
 - Desinscrever um participante de um evento - para não atrapalhar a organização do evento **(precisa do id do usuario)**
   - Criar página "participando" 
   
 - Paginação
   - Falta ajeitar para não sobrepor a pesquisa
   - Falta fazer o javaScript e os metodos para a barra de numero de páginas
 
 ## Perguntas: 
 - O usuario pode escolher ser organizador logo no cadastro inicial?
 
## APIs:
 - Validar email
 - Validar Nomes ofensivos
 -
 


