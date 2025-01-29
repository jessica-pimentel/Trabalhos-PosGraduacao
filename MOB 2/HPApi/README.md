# Harry Potter API App (HPApp)

O **HPApp** é um aplicativo Android que permite aos usuários interagir com uma API do universo de Harry Potter. Ele oferece funcionalidades para listar professores, estudantes por casa e buscar informações detalhadas de personagens a partir de um ID fornecido. O aplicativo foi desenvolvido em Kotlin e segue uma arquitetura de múltiplas `Activities` para organizar as funcionalidades.

## 📱 Funcionalidades

- **Buscar Personagem por ID**: Insira o ID de um personagem para visualizar detalhes como nome e casa.
- **Listar Professores**: Exibe uma lista de professores de Hogwarts com IDs e nomes, permitindo copiar o ID de qualquer professor com um toque.
- **Listar Estudantes por Casa**: Permite selecionar uma casa de Hogwarts e exibir a lista de estudantes associados.
- **Navegação entre Activities**: Utiliza múltiplas telas para organizar as funcionalidades do aplicativo.
- **Toast Notifications**: Mensagens rápidas para validar ações, como IDs inválidos ou casas não selecionadas.

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal para o desenvolvimento.
- **Android SDK**: APIs de UI e componentes de Android.
- **RecyclerView**: Utilizado para exibição eficiente de listas de professores e estudantes.
- **Retrofit**: Biblioteca para consumo da API REST do Harry Potter.
- **Gson**: Conversão automática de JSON para objetos Kotlin.
- **Coroutines**: Gerenciamento de chamadas assíncronas à API.
- **Toast Notifications**: Mensagens de feedback para o usuário.
- **Intents**: Navegação entre Activities do aplicativo.

## ⚙️ Como Funciona

- **MainActivity**:
  - Exibe opções para acessar as funcionalidades principais: buscar personagem por ID, listar professores e listar estudantes por casa.
  - Botão de "Sair" fixado na parte inferior da tela para encerrar o aplicativo.

- **CharacterByIdActivity**:
  - Permite inserir um ID e buscar informações detalhadas de um personagem.
  - Exibe detalhes do personagem ou uma mensagem de erro caso o ID seja inválido.

- **HogwartsStaffActivity**:
  - Exibe uma lista de professores com IDs e nomes.
  - IDs podem ser copiados para a área de transferência ao tocar no item.

- **StudentsByHouseActivity**:
  - Permite selecionar uma casa (Gryffindor, Hufflepuff, Ravenclaw ou Slytherin) e listar os estudantes associados.
  - Exibe a lista em um formato organizado com IDs e nomes.

## 🚀 Como Executar

1. Clone este repositório.
2. Abra o projeto no Android Studio.
3. Configure um emulador Android ou conecte um dispositivo real.
4. Compile e execute o aplicativo.

## 🖼️ Layouts e Experiência do Usuário

- **Interface Intuitiva**: As telas são simples e organizadas, com botões de navegação claros e feedback visual para ações.
- **Design Responsivo**: Os botões são dimensionados uniformemente, e as listas suportam rolagem para visualização de grandes volumes de dados.
- **Título no Topo**: Cada tela possui um título que descreve sua funcionalidade.

## 🌐 API Utilizada

- **[HP API](https://hp-api.onrender.com/)**:
  - **Endpoints Utilizados**:
    - `/api/characters/staff`: Para listar professores.
    - `/api/characters/house/{house}`: Para listar estudantes por casa.
    - `/api/character/{id}`: Para buscar detalhes de personagens.

## 🔑 Pontos de Destaque

- **Integração com API REST**: Consome dados em tempo real da API do universo de Harry Potter.
- **Gestão de Erros**: Mensagens claras para lidar com entradas inválidas ou falhas de conexão.
- **Copiar ID**: Função prática para copiar IDs diretamente de listas exibidas.

O **Harry Potter API App** é uma ferramenta prática e educativa, perfeita para quem deseja explorar os dados do universo de Harry Potter ou aprender mais sobre o desenvolvimento de aplicativos Android.
