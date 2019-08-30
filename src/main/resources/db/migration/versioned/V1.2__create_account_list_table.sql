CREATE TABLE account_list (
    account_uuid UUID NOT NULL REFERENCES account(uuid),
    list_uuid UUID NOT NULL REFERENCES list(uuid),
    can_view_list BOOLEAN NOT NULL DEFAULT FALSE,
    can_edit_list BOOLEAN NOT NULL DEFAULT FALSE,
    can_delete_list BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY(account_uuid, list_uuid)
);
