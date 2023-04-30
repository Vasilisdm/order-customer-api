MERGE INTO orders (id,  created_at)
    key (id)
    values ( 1, CURRENT_TIMESTAMP );


MERGE INTO items (id, order_id, name, quantity, price)
    key (id)
    values ( 1, 1, 'MX MECHANICAL MINI FOR MAC', 1, 159.99 )