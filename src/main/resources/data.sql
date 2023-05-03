CREATE TABLE book (
  id UUID PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(50) NOT NULL,
  price LONG NOT NULL,
  isbn VARCHAR(50) NOT NULL
);

INSERT INTO book VALUES
    (UUID(), 'Skip and Loafer Vol. 1', 'Misaki Takamatsu', 225000, '9781648275883'),
    (UUID(), 'Blue Box Vol. 1', 'Kouji Miura', 168000, '9781974734627'),
    (UUID(), 'Dorohedoro Vol. 1', 'Q. Hayashida', 235000, '9781421533636'),
    (UUID(), 'Chainsaw Man Vol. 1', 'Tatsuki Fujimoto', 170000, '9781974709939'),
    (UUID(), 'Genshiken Omnibus Vol. 1', 'Shimoku Kio', 440000, '9781935429364');
