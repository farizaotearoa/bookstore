# Bookstore
Bookstore Rest API

I'm creating REST API for bookstore using Java Spring Boot and H2 as in-memory database

# List of Endpoints

## 1. /books (GET Method)

Get all books from bookstore database.

Sample Response:

```json
[
  {
    "id": "8f675a5e-df0d-4abd-bcb2-62923a02f1f6",
    "title": "Skip and Loafer Vol. 1",
    "author": "Misaki Takamatsu",
    "price": 225000,
    "isbn": "9781648275883"
  },
  {
    "id": "fe9d6cb9-c7bc-4bd9-a2d0-afba85e16947",
    "title": "Blue Box Vol. 1",
    "author": "Kouji Miura",
    "price": 168000,
    "isbn": "9781974734627"
  },
  ...
]
```

## 2. /books/{id} (GET Method)

Get books by ID from bookstore database.

Sample Response:

```json
{
  "id": "8f675a5e-df0d-4abd-bcb2-62923a02f1f6",
  "title": "Skip and Loafer Vol. 1",
  "author": "Misaki Takamatsu",
  "price": 225000,
  "isbn": "9781648275883"
}
```

## 3. /books/ (POST Method)

Insert a new book into the bookstore database, with a randomly generated ID.

Sample Request Body:
```json
{
  "title": "Dorohedoro Vol. 1",
  "author": "Q. Hayashida",
  "price": 235000,
  "isbn": "9781421533636"
}
```

Sample Response:
```json
{
  "id": "f7bc8c5e-f830-4762-acfc-401c41f7ecc3",
  "title": "Dorohedoro Vol. 1",
  "author": "Q. Hayashida",
  "price": 235000,
  "isbn": "9781421533636"
}
```
## 4. /books/{id} (PUT Method)

Update the book attributes such as title, author, price, and ISBN. 
The request body does not include the ID field as it is passed through the Path Variable.

Sample Request Body:
```json
{
  "title": "Dorohedoro Vol. 1",
  "author": "Q. Hayashida",
  "price": 238000,
  "isbn": "9781421533636"
}
```

Sample Response:
```json
{
  "id": "f7bc8c5e-f830-4762-acfc-401c41f7ecc3",
  "title": "Dorohedoro Vol. 1",
  "author": "Q. Hayashida",
  "price": 238000,
  "isbn": "9781421533636"
}
```

## 5. /books/{id} (DELETE Method)

Delete book from bookstore database.

