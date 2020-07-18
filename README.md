# PostrBackend
## Description
Backend part of Postr application.
### Tech
* Java
* SpringBoot
* Lombok
* Hibernate
* JPA 
* Firebase Cloud Messaging
* Firebase Auth
* PosrgreSQL
### End points with methods
### GET
* Get all comments in given event (id)

`/api/comments/{id}`

* Get all events

`/api/events/`

* Get all events in given range from given coordinates

`/api/events/{lat}/{lon}/{range}`

* Get all events followed by user

`/api/follows`

* Get all users who send a message to current user

`/api/authors`

* Get new messages between users

`/api/messages/aft/{uid2}/{messId}`

* Get all messages between users

`/api/messages/{uid2}`

* Get current user info

`/api/user`

### POST

* Add new comment

`/api/comment`

* Add new event

`/api/event`

* Add new follow

`/api/follow`

* Add new message

`/api/message`

* Add new user

`/api/user`

### PUT

* Edit event

`/api/event`

* Edit user

`/api/user`

### DELETE

* Unfollow event (id)

`/api/follow/{id}`
