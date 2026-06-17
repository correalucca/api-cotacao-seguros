CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255)
);

CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(20),
    email VARCHAR(255),
    telefone VARCHAR(20)
);

CREATE TABLE veiculo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20),
    modelo VARCHAR(255),
    marca VARCHAR(255)
);

CREATE TABLE cotacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    veiculo_id BIGINT,
    valor DECIMAL(19,2),
    data_criacao TIMESTAMP,
    CONSTRAINT fk_cotacao_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CONSTRAINT fk_cotacao_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);