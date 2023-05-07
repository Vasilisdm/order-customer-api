MERGE INTO orders (id,  created_at)
    key (id)
    values ( 10001, CURRENT_TIMESTAMP );


MERGE INTO orderItems (id, order_id, name, quantity, price)
    key (id)
    values ( 10001, 10001, 'MX MECHANICAL MINI FOR MAC', 1, 159.99 )