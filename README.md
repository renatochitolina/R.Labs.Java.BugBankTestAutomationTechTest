![Logo](logo.png)

# Boilerplate Java para Automação de Testes de Aplicações Web

O boilerplate Java para automação de testes de aplicações web contém um conjunto inicial de recursos que possibilitam um rápido e simplificado início de construção de testes automatizados utilizando a proposta "DB" do modelo [PageObject](https://martinfowler.com/bliki/PageObject.html).

## Getting started

### Pré-requisitos

- [Java SE Development Kit 11](https://www.oracle.com/br/java/technologies/downloads/#java11) ou superior
- [Gradle 6.8](https://docs.gradle.org/6.8/userguide/installation.html#installing_with_a_package_manager) ou superior (se superior, demanda atualização do Gradle Wrapper)

### Workspace

O workspace contém a seguinte estrutura:

```shell
.
├── gradle
└── src
    ├── main
    │   ├── java
    │   │   ├── app
    │   │   └── framework
    │   └── resources
    └── test
        ├── java
        │   ├── pageobjects
        │   ├── tasks
        │   ├── testcases
        │   └── validations
        └── resources
```

- `gradle` - Local da distribuição Gradle em utilização no projeto (Gradle Wrapper)
- `src` - Local dos arquivos de código fonte da codebase
  - `main` - Local que centraliza a parte da codebase a ser executada em formato de aplicação convencional (Java ou outras)
    - `java/app` - Local do web server que provê uma aplicação web de exemplo (cujos arquivos HTML, JavaScript e CSS estão em `src/main/resources/app`)
    - `java/framework` - Local da parte base do framework para automação de testes
    - `resources` - Local de arquivos utilitários (ex.: arquivos da aplicação web de exemplo, arquivos de dados)
  - `test` - Local que centraliza a parte da codebase a ser executada em formato de testes (Java ou outras, via frameworks como por exemplo JUnit)
    - `java/pageobjects` - Local complementar ao framework para automação de testes que possui as classes que representam as páginas da aplicação web alvo (ex.: aplicação provida pelo web server em `src/main/java/app`)
    - `java/tasks` - Local complementar ao framework para automação de testes que possui as classes que representam as ações de alto nível a serem efetuadas nas páginas da aplicação web alvo via `pageobjects`
    - `java/testcases` - Local complementar ao framework para automação de testes que possui as classes que representam os casos de teste de fato, através do uso em conjunto de `pageobjects`, `tasks` e `validations`
    - `java/validations` - Local complementar ao framework para automação de testes que possui as classes que representam as "assertions" (verificações de resultados)
    - `resources` - Local de arquivos utilitários para os testes (ex.: arquivos de dados, output padrão de relatórios de execução de testes)

> [!NOTE]
> As pastas intermediárias `java` contidas em `main` e `test` tem propósito de centralizar os arquivos Java. Essa convenção ajuda na organização do código e é flexível para suportar outras linguagens (Gradle suporta ambientes multi-linguagem). Se houvessem também arquivos em uma linguagem diferente, como por exemplo Kotlin, também poderia haver uma pasta correspondente (ex.: `kotlin`).

### Executando o projeto

#### 1. Aplicação

##### 1.1 Via terminal de comandos

Atualizar (sincronizar) as dependências configuradas no projeto
```shell
gradle --refresh-dependencies
```

Executar o arquivo `gradlew` usando a task **run** 
```shell
./gradlew run
```

#### 2. Testes

##### 2.1 Via terminal de comandos

Atualizar (sincronizar) as dependências configuradas no projeto
```shell
gradle --refresh-dependencies
```

Executar o arquivo `gradlew` usando a task **test** 
```shell
./gradlew test
```

# Desafio Automação BugBank

A aplicação web [BugBank](https://bugbank.netlify.app) simula um módulo financeiro, possibilitando registros de contas de usuário que também funcionam como contas bancárias capazes de efetuar transferências de valores monetários para outras contas também registradas.

## Desafio

### 1. Automation Request

#### Caso de Teste (TC - Test Case):

Registro de uma nova conta de usuário

#### Descrição:

Validar o registro de uma nova conta de usuário através de uma tentativa de acesso bem sucedida.

#### Pré-Condições:

- A aplicação web deve estar acessível
- O usuário ainda não deve ter uma conta registrada

#### Passos:

1. Abrir a página inicial da aplicação web
2. Clicar no botão `Registrar`
3. Preencher o formulário de registro com informações válidas do usuário (E-mail, Nome, Senha e Confirmação senha).
4. Ativar a opção `Criar conta com saldo?`
5. Clicar no botão `Cadastrar`
6. Clicar no botão `Fechar` da mensagem `criada com sucesso`
7. Preencher o formulário de acesso com informações válidas da conta do usuário (E-mail e Senha)
8. Clicar no botão `Acessar`

#### Resultados Esperados:

- Após o registro, uma mensagem `criada com sucesso` deve ser exibida
- Após o acesso, uma tela de gestão da conta deve ser exibida contendo um elemento *Saldo em conta*

#### Pós-Condições:

- O usuário deve estar com a nova conta registrada e ter a acessado com sucesso

### 2. Automation Request

#### Caso de Teste (TC - Test Case):

Transferência de valores monetários entre contas de usuário

#### Descrição:

Validar em uma conta de usuário destinatária a entrada de um valor monetário proveniente de uma conta de usuário originária.

#### Pré-Condições:

- A aplicação web deve estar acessível
- Devem haver duas contas de usuário já registradas (originária e destinatária), tendo a conta originária um *Saldo em conta* de R$ 1000,00, e a conta destinatária um *Saldo em conta* de R$ 0,00

#### Passos:

1. Abrir a página inicial da aplicação web
2. Preencher o formulário de acesso com informações válidas da conta de usuário originária (E-mail e Senha)
3. Clicar no botão `Acessar`
4. Clicar no botão `Transferência`
5. Preencher o formulário de transferência com informações válidas para execução da transferência (Número da conta de usuário destinatária, Dígito da conta de usuário destinatária, Valor e Descrição) - O valor deve ser de R$ 500,00
6. Clicar no botão `Transferir agora`
7. Clicar no botão `Fechar` da mensagem `realizada com sucesso`
8. Clicar no botão `Sair`
9. Preencher o formulário de acesso com informações válidas da conta de usuário destinatária (E-mail e Senha)
10. Clicar no botão `Acessar`

#### Resultados Esperados:

- Após a transferência, a conta de usuário originária deve estar com *Saldo em conta* de R$ 500,00
- Após o acesso à conta de usuário destinatária, ela deve estar com *Saldo em conta* de R$ 500,00

#### Pós-Condições:

- As duas contas de usuário devem estar com valores iguais de *Saldo em conta*
