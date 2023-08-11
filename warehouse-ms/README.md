# Warehouse-ms

## Endpoints
- BaseURL: /warehouses
- POST: create()
- GET: getAll()
- GET /{id}: getById()
- DELETE /{id}: delete()

## Model
```json
    {
        "id": 1,
        "productId": 1,
        "quantity": 10
    }
```

## Business Rules
- O estoque é responsável por avisar se o produto está ou não com quantidade do produto 