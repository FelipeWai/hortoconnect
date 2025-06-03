<h1 align="center">
  <a href="https://github.com/JoaoPedroOM">
    <img src="https://i.ibb.co/6RgfCdbM/logo.png" alt="HortoConnect" width="200">
  </a>
  <br>
  Horto Connect
</h1>

<h4 align="center">Conectando fornecedores e pequenos comerciantes.</h4>

---

## 🌐 Language

- [🇧🇷 Português](#-portuguese-br)
- [🇺🇸 English](#-english-version)

---

## 🇧🇷 Portuguese (BR)

### 🌱 Sobre a Horto Connect

O Horto Connect nasceu com um propósito direto e necessário: conectar pequenos comerciantes a uma grande variedade de fornecedores de frutas, verduras e legumes, facilitando o acesso a melhores preços e ampliando as possibilidades de escolha.

Sabemos que muitos desses comerciantes ainda enfrentam dificuldades para encontrar fornecedores confiáveis, comparar preços ou fechar pedidos de forma prática. Do outro lado, os fornecedores também sentem a necessidade de alcançar novos clientes de forma organizada e profissional. Foi aí que o Horto Connect entrou na jogada.

Desenvolvemos uma plataforma moderna e intuitiva, onde os fornecedores podem divulgar seus produtos, criar seus perfis e gerenciar planos de assinatura com pagamento recorrente. Toda a comunicação é feita de forma segura e direta — e o sistema de pagamento via Pix ou cartão, com QR Code gerado automaticamente, torna tudo mais fácil e rápido.

Para os comerciantes, a vantagem é clara: acesso centralizado a uma vitrine de produtos frescos, com possibilidade de buscar por categoria, preço, localização e muito mais. Isso poupa tempo, reduz custos e aumenta a competitividade.

Além disso, o projeto foi pensado para crescer com a comunidade: nosso plano é seguir ouvindo os usuários, melhorando continuamente a plataforma e mantendo tudo simples, eficiente e com foco real em quem movimenta o dia a dia das feiras, hortas e mercados locais.

Com o Horto Connect, queremos que comprar e vender no atacado seja tão fácil quanto fazer uma compra online — mas com aquele toque de cuidado e confiança que só quem vive do hortifruti entende.

---

### 🚀 Tecnologias Utilizadas

#### BackEnd

- Java 17
- Spring Boot
- Spring Security
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway (controle de versão do banco de dados)
- JWT (autenticação e autorização)
- Testes Unitários com JUnit5 e Mockito
- BCrypt (hash de senhas)
- Mailgun API (envio de e-mails com templates dinâmicos)
- AWS EC2 (deploy da aplicação)
- AWS Secrets Manager (segurança de credenciais)
- Maven
- GitHub Actions (CI/CD)

---

### 🌽 Funcionalidades do Projeto

- **Cadastro de Fornecedores e Login**  
  Fornecedores podem criar uma conta de forma simples e segura. Após o cadastro, têm acesso a um painel exclusivo onde podem gerenciar seus produtos, plano de assinatura, perfil e informações comerciais com autonomia total.

- **Planos com Pagamento Recorrente**  
  O Horto Connect oferece diferentes planos mensais para fornecedores, que podem ser pagos via **Pix** ou **cartão de crédito**, com **integração automática com o Mercado Pago**. Após o pagamento, o fornecedor tem acesso completo à plataforma por 30 dias. A renovação pode ser feita diretamente pelo painel.

- **Cadastro e Gestão de Produtos**  
  O fornecedor pode cadastrar seus produtos com nome, descrição, preço por unidade ou por quilo, categoria (fruta, verdura, legume etc.), foto e disponibilidade. É possível editar ou remover produtos a qualquer momento, mantendo a vitrine sempre atualizada.

- **Dashboard do Fornecedor**  
  No painel, o fornecedor pode acompanhar o status do seu plano, gerenciar seus produtos, visualizar pedidos recebidos (em futuras versões), editar dados do perfil e acessar relatórios básicos de visualização e interesse dos comerciantes.

- **Busca de Produtos com Filtros Inteligentes**  
  Comerciantes podem buscar produtos usando filtros como nome, categoria, preço e localização do fornecedor. Isso facilita a comparação de preços e ajuda na tomada de decisão de forma rápida e eficiente.

- **Página Pública de Perfil do Fornecedor**  
  Cada fornecedor possui uma página pública personalizada, com seu catálogo de produtos, informações de contato e dados básicos do perfil comercial. Isso ajuda os comerciantes a conhecerem melhor quem está por trás da oferta.

- **Sistema de Autenticação com JWT**  
  Toda a autenticação e segurança do sistema é feita com **JSON Web Tokens (JWT)**, garantindo que cada fornecedor tenha controle seguro sobre sua conta e informações.

- **Geração de QR Code para Pagamentos**  
  Ao escolher pagar via Pix, o sistema gera automaticamente um **QR Code com a chave do fornecedor**, facilitando a finalização do pagamento e acelerando o processo de ativação do plano.

- **Sistema Modular e Escalável**  
  A estrutura do projeto foi pensada para permitir novas funcionalidades como pedidos diretos, avaliações de fornecedores, notificações em tempo real e integração com ferramentas de logística em versões futuras.

---

## 🇺🇸 English Version

### 🌱 About Horto Connect

Horto Connect was created with a direct and necessary mission: to connect small retailers with a wide variety of fruit and vegetable suppliers, making it easier to access better prices and more purchasing options.

Many merchants still struggle to find trustworthy suppliers, compare prices, or place orders in a practical way. On the other side, suppliers also need a professional and organized way to reach new clients. That’s where Horto Connect comes into play.

We developed a modern, user-friendly platform where suppliers can promote their products, manage their profiles, and control their subscription plans with recurring payments. Communication is secure and direct — and the payment system via Pix or credit card, with automatically generated QR Codes, makes everything easier and faster.

For merchants, the advantage is obvious: centralized access to a fresh product showcase, with search filters by category, price, location, and more. This saves time, reduces costs, and increases competitiveness.

Moreover, the project is designed to grow with the community: we aim to continuously listen to users, improve the platform, and keep everything simple, efficient, and focused on the people who run local markets and produce businesses every day.

With Horto Connect, buying and selling wholesale should be as easy as online shopping — but with the trust and care that only those who truly understand the produce market can offer.

---

### 🚀 Technologies Used

#### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway (database version control)
- JWT (authentication and authorization)
- Unit Testing with JUnit5 and Mockito
- BCrypt (password hashing)
- Mailgun API (email delivery with dynamic templates)
- AWS EC2 (application deployment)
- AWS Secrets Manager (credential security)
- Maven
- GitHub Actions (CI/CD)

---

### 🌽 Project Features

- **Supplier Registration and Login**  
  Suppliers can easily and securely create an account. After registering, they gain access to an exclusive dashboard where they can manage their products, subscription plan, profile, and commercial information.

- **Recurring Payment Plans**  
  Horto Connect offers monthly plans for suppliers, payable via **Pix** or **credit card**, with **automatic integration with Mercado Pago**. After payment, suppliers gain full access to the platform for 30 days. Renewals can be managed directly from the dashboard.

- **Product Registration and Management**  
  Suppliers can register products with name, description, unit or per-kilo price, category (fruit, vegetable, etc.), photo, and availability. Products can be updated or removed anytime, keeping the catalog up-to-date.

- **Supplier Dashboard**  
  In the dashboard, suppliers can check their subscription status, manage products, view orders (in future versions), edit profile data, and access basic insights about merchant engagement.

- **Smart Product Search Filters**  
  Merchants can search for products using filters like name, category, price, and supplier location. This makes price comparison quick and efficient.

- **Public Supplier Profile Page**  
  Each supplier has a personalized public profile showing their product catalog, contact info, and basic business details. This helps merchants know who they're buying from.

- **JWT-Based Authentication System**  
  The entire platform uses **JSON Web Tokens (JWT)** for secure authentication and data protection.

- **QR Code Generation for Payments**  
  When paying with Pix, the system automatically generates a **QR Code with the supplier's Pix key**, making payment fast and easy.

- **Modular and Scalable System**  
  The project architecture is ready for future features like direct orders, supplier reviews, real-time notifications, and logistics integrations.

---

