CREATE TABLE public.items
(
    id VARCHAR(30) NOT NULL,
    value jsonb NULL DEFAULT NULL,
    created_at TIMESTAMP(6) NULL DEFAULT NULL,
    modified_at TIMESTAMP(6) NULL DEFAULT NULL,
    CONSTRAINT bills_pkey PRIMARY KEY (id)
);