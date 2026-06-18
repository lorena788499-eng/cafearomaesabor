-- Script SQL para criação manual das tabelas (opcional - Hibernate faz isso automaticamente)
-- Database: CafeAromaESabor

-- Tabela de Usuários
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_usuario VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_nome_usuario (nome_usuario),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela de Produtos
CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    estoque_minimo INT NOT NULL,
    lote VARCHAR(50) NOT NULL,
    data_validade DATE NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_codigo (codigo),
    INDEX idx_nome (nome),
    INDEX idx_data_validade (data_validade),
    INDEX idx_categoria (categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela de Movimentações de Estoque
CREATE TABLE movimentacoes_estoque (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL,
    data_movimentacao DATE NOT NULL,
    observacoes TEXT,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    INDEX idx_produto_id (produto_id),
    INDEX idx_usuario_id (usuario_id),
    INDEX idx_data_movimentacao (data_movimentacao),
    INDEX idx_tipo (tipo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar usuários de teste
INSERT INTO usuarios (nome_usuario, senha, nome_completo, email, ativo) VALUES
('admin', '123456', 'Administrador do Sistema', 'admin@cafe.com', TRUE),
('joao', '123456', 'João Silva', 'joao@cafe.com', TRUE);

-- Insertar produtos de teste
INSERT INTO produtos (codigo, nome, descricao, categoria, quantidade, estoque_minimo, lote, data_validade) VALUES
('CAF001', 'Café Arábica Premium', 'Café de origem única, arábica 100%', 'cafe', 45, 20, 'L12345', '2025-12-25'),
('CAF002', 'Café Robusta Forte', 'Café robusta com corpo forte', 'cafe', 30, 15, 'L12346', '2025-08-10'),
('CAF003', 'Café Moído Espresso', 'Café moído para máquina espresso', 'cafe_moido', 12, 25, 'L12347', '2025-06-30'),
('CHA001', 'Chá Verde Premium', 'Chá verde de alta qualidade', 'cha', 28, 10, 'L12348', '2025-10-15'),
('ACC001', 'Moedor de Café Manual', 'Moedor manual para café grão', 'acessorios', 8, 5, 'L12349', '2026-12-31'),
('ALI001', 'Açúcar Cristal', 'Açúcar cristal refinado', 'alimentos', 50, 30, 'L12350', '2026-05-20'),
('CAF004', 'Café Torrado Médio', 'Café torrado ponto médio', 'cafe', 35, 20, 'L12351', '2025-11-18'),
('CAF005', 'Café Descafeinado', 'Café descafeinado premium', 'cafe', 22, 15, 'L12352', '2025-09-14'),
('ACC002', 'Filtro de Café Reutilizável', 'Filtro de inox reutilizável', 'acessorios', 60, 30, 'L12353', '2026-12-31'),
('ALI002', 'Leite em Pó Integral', 'Leite em pó integral', 'alimentos', 24, 15, 'L12354', '2026-03-12'),
('CHA002', 'Chá Preto Inglês', 'Chá preto tipo inglês clássico', 'cha', 18, 12, 'L12355', '2025-07-22'),
('ACC003', 'Xícara de Café Cerâmica', 'Xícara de cerâmica resistente', 'acessorios', 100, 50, 'L12356', '2027-12-31');

