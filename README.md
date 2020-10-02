# AmazonEvent
***Importante --> Descompactar a as pastas .idea e .mvn***

## O que já foi implementado:

(Itaciane)
 - Criação de evento
 - Eventos na página principal
 - Participação em evento
 - Listagem de participantes
 - Listagem de eventos
 - Impressão de lista de participantes em PDF
 - Página principal
 - Página de detalhes do evento
 - Página de gerenciamento de eventos 
 - Página participantes
 
(Marcelo) 
 - Responsividade 
 - Carroussel 
 - Impressão de lista de presença em PDF
 - Gerenciamento de eventos
 - Pesquisa por titulo (pagina principal - todos)
 - Pesquisa por titulo (TodosEventos - Moderador)
 - Pesquisa por titulo (Meus eventos - Organizador)
 - Pesquisa de participantes
 - Pesquisa de usuarios
 - CRUD de evento
 - Login e cadastro (spring security)
 - Página gerenciar eventos (moderador)
 - Página gerenciar usuarios
 - Código já reduzido com o fragment (o que deu pra fazer ate momento)
 - Acessar ao perfil do usuário na página "Minha conta"

## O que falta ajeitar:
 - Imprimir lista de presença e lista de participantes --> ***passar tudo para html e js***
 
 - Ajeitar o rodapé
   - deixar o rodapé na parte de baixo mesmo se tiver espaço no meio da tela

 - O participante deve ter o id de quem o cadastrou - para evitar flood **(precisa do id do usuario)**
 - A pesquisa pode sobreescrever a paginação mas não o contrário

 - Restringir para apenas os donos do perfil visualizarem editarem seu perfil
 
## O que falta implementar:   
 - Mensagens
   - Cadastro realizado com sucesso
   - Evento criado com sucesso
   - Inscrição realizada com sucesso
   - Evento editado
   - Evento exluído
   - Perfil editado
   
 - Mensagens de erro
   - Login
   - Cadastro
   - Criação de evento
   - Inscrição
   - Evento não encontrado(pesquisa)
   - Participante não encontrado(pesquisa)
   - Usuario não encontrado(pesquisa)
   
 - Emissão de certificado --> ***passar tudo para html e js***
   - Enviar certificado por email
 
 - Editar o perfil do usuário na página "Minha conta"
   - Falta ter ideia de como faz
   
 - Desinscrever um participante de um evento - para não atrapalhar a organização do evento **(precisa do id do usuario)**
   - Criar página "participando" 
   
 - Paginação
   - Falta ajeitar para não sobrepor a pesquisa
   - Falta fazer o javaScript e os metodos para a barra de numero de páginas
 
 ## Perguntas: 
 - O usuario pode escolher ser organizador logo no cadastro inicial?
 
## APIs que serão implementadas:
 - Validar email
 - Validar Nomes ofensivos
 
 


