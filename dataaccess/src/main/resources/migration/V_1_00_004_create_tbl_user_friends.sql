create table user_friends
(
    owner_id   UUID NOT NULL,
    friend_id  UUID NOT NULL,
    CONSTRAINT fk_frnds_owner_id FOREIGN_KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE DEFERRABLE,
    CONSTRAINT fk_frnds_frnd_id FOREIGN_KEY (friend_id) REFERENCES users(id) ON DELETE CASCADE DEFERRABLE
);

create table user_friends_aud
(
    owner_id  UUID                     NOT NULL,
    friend_id UUID                     NOT NULL,
    rev       timestamp with time zone NOT NULL default now(),
    revtype   integer
)