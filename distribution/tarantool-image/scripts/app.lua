function start()

    box.schema.space.create('users');
    box.space.users:format({
        { name = 'id', type = 'string' },
        { name = 'username', type = 'string' },
        { name = 'first_name', type = 'string' },
        { name = 'last_name', type = 'string' },
        { name = 'age', type = 'unsigned' },
        { name = 'interests', type = 'string' },
        { name = 'city', type = 'string' }
    });
    box.space.users:create_index('pk', {
        type = 'HASH',
        parts = { 'id'}
    });
    box.space.users:create_index('first_name_last_name_idx', {
        type = 'tree',
        unique = false,
        parts = {{ field='first_name', type='string'}, { field='last_name', type='string'}}
    });
    box.space.users:create_index('first_name_idx', {
        type = 'tree',
        unique = false,
        parts = { 'first_name'}
    });
    box.space.users:create_index('last_name_idx', {
        type = 'tree',
        unique = false,
        parts = { 'last_name'}
    });
    --box.space.users:insert{'1', 'evgeny_123', 'evgeny_123', 'holuhoev_123', 23, 'interests', 'city'}
    --box.space.users:insert{'2', 'evgeny_456', 'evgeny_456', 'holuhoev_456', 23, 'interests', 'city'}
end
