# API

## API Links

[User Controller](#user-controller)
* [User Entity](#User-Entity)
* [Register](#Register)
* [Login](#Login)
* [Update User Information](#Update-User-Information)
* [Get A User Information](#Get-A-User-Information)
* [Reset Password](#Reset-Password)
* [Update User Avatar](#Update-User-Avatar)
* [Get User Avatar](#Get-User-Avatar)

[Game Controller](#game-controller)
* [Game Entity](#Game-Entity)
* [Game Publish](#Game-Publish)
* [View A Game](#View-A-Game)
* [View All Game](#View-All-Game)
* [Delete A Game](#Delete-A-Game)
* [Add A Game To User](#Add-A-Game-To-User)
* [Delete A Game To User](#Delete-A-Game-To-User)
* [View User Game](#View-User-Game)
* [Search Game With Name](#Search-Game-With-Name)
* [Upload Game Image](#Upload-Game-Image)
* [Get Game Image](#Get-Game-Image)
* [Update Game Information](#Update-Game-Information)
* [Check if user own game](#Check-if-user-own-game)


[Friendship Controller](#friendship-controller)
* [Friendship Entity](#Friendship-Entity)
* [FriendRequest Entity](#FriendRequest-Entity)
* [Send a friend request](#Send-a-friend-request)
* [Get friend requests](#Get-friend-requests)
* [Accept a friend request](#Accept-a-friend-request)
* [Get users' friends](#Get-users'-friends)
* [Delete a friend](#Delete-a-friend)

[Club Controller](#club-controller)
* [Club Entity](#Club-Entity)
* [UserClub Entity](#UserClub-Entity)
* [UserClubResp Entity](#UserClubResp-Entity)
* [Create a club](#Create-a-club)
* [Delete a club](#Delete-a-club)
* [Join a club](#Join-a-club)
* [Quit a club](#Quit-a-club)
* [Get users' clubs](#Get-users'-clubs)
* [Search clubs](#Search-clubs)
* [Get club members](#Get-club-members)

[Forum Controller](#forum-controller)
* [Forum Entity](#Forum-Entity)
* [ForumPost Entity](#ForumPost-Entity)
* [ForumReply Entity](#ForumReply-Entity)
* [Get forum info](#Get-forum-info)
* [Get posts](#Get-posts)
* [Create a post](#Create-a-post)
* [Delete a post](#Delete-a-post)
* [Get replies](#Get-replies)
* [Add a reply](#Add-a-reply)
* [Delete a reply](#Delete-a-reply)
* [Stick a post](#Stick-a-post)
* [Join a forum](#Join-a-forum)
* [Quit a forum](#Quit-a-forum)
* [Forum member check](#Forum-member-check)

[Sell Controller](#sell-controller)
* [Sell Entity](#Sell-Entity)
* [Post a Sell](#Post-a-Sell)
* [Delete a Sell](#Delete-a-Sell)
* [Update a Sell](#Update-a-Sell)
* [View a Game's Sell](#View-a-Game's-Sell)
* [View a User's Sell](#View-a-User's-Sell)
* [View a Sell](#View-a-Sell)

[Review Controller](#review-controller)
* [Review Entity](#Review-Entity)
* [Post a Review](#Post-a-Review)
* [Delete a Review](#Delete-a-Review)
* [Update a Review](#Update-a-Review)
* [View a Game's Review](#View-a-Game's-Review)
* [View a User's Reveiw](#View-a-User's-Reveiw)
* [View a Review](#View-a-Review)
* [Check if user has a Review for a game](#Check-if-user-has-a-Review-for-a-game)

## User Controller

### User Entity

{\
    "id": [String],\
    "userName": [String],\
    "email": [String],\
    "avatar": [String],\
    "city": [String],\
    "gender": [Integer],\
    "age": [Integer]\
}

### Register

* URL: /api/user/register
* Method: POST
* RequestBody:\
{\
    "userName": [String],\
    "password": [String],\
    "email": [String],\
    "city": [String],\
    "gender": [Integer],\
    "age": [Integer]\
}
* Success Response
    * Status: 200
    * Content: {User}
* Error Response
    * Status: 500

### Login

* URL: /api/user/login
* Method: POST
* RequestBody
{\
    "userName": [String],\
    "password": [String],\
}
* Success Response
    * Status: 200
    * Content: {User}
* Error Response
    * Status: 200
    * Content: null

### Update User Information

* URL: /api/user/updateUserInfo
* Method: PUT
* Parameters
    * Required: 
        * uid=[String]
    * Optional:
        * userName=[String]
        * email=[String]
        * city=[String]
        * gender=[Integer] (0 male, 1 female)
        * age=[Integer]
* Success Response
    * Status: 200
    * Content: {User}
* Error Response
    * Status: 200
    * Content: null

### Get A User Information

* URL: /api/user/getUserInfo
* Method: GET
* Parameters
    * Required: 
        * uid=[String]
        * targetId=[String]
* Success Response
    * Status: 200
    * Content: 
    {\
        "user": [User],\
        "isFriend": [Integer]\
    }
* Error Response
    * Status: 500
* Note: 
    * uid = The user who is getting the information
    * targetId = The user whose information will be fetched

### Reset Password

* URL: /api/user/resetPwd
* Method: PUT
* Parameters
    * Required: 
        * uid=[String]
        * oldPwd=[String]
        * newPwd=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500\
    Or    
    * Status: 200
    * Content: 0

### Update User Avatar

* URL: /api/user/updateAvatar
* Method: POST
* Parameters
    * Required: 
        * uid=[String]
        * avatar=[Multipart]
* Success Response
    * Status: 200
    * Content: {filename}
* Error Response
    * Status: 200
    * Content: null\
    Or
    * Status: 500

### Get User Avatar

* URL: /api/user/getAvatar
* Method: GET
* Parameters
    * Required: 
        * uid=[String]
* Success Response
    * Status: 200
    * Content: {Image}
* Error Response
    * Status: 500

## Game Controller

### Game Entity

{\
    "id": [String],\
    "gameName": [String],\
    "description": [String],\
    "artist": [String],\
    "designer": [String],\
    "publisher": [String],\
    "timePerRound": [Integer],\
    "year": [Integer],\
    "playerAge": [Integer]\
    }

### Game Publish

* URL: /api/game/publish
* Method: POST
* RequestBody:\
{\
"gameName": [String],\
"publisher": [String],\
"description": [String],\
"artist": [String],\
"designer": [String],\
"timePerRound": [Integer],\
"year": [Integer],\
"playerAge": [Integer]\
}
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500

### View A Game

* URL: /api/game/view
* Method: Get
* Parameters
    * Required: 
        * gameId=[String]
* Success Response
    * Status: 200
    * Content: {Game}      
* Error Response
    * Status: 500

### View All Game

* URL: /api/game/viewAll
* Method: Get
* Parameters
    * Required: 
        * pageSize=[Integer]
        * pageNo=[Integer]
* Success Response
    * Status: 200
    * Content: 
    {\
    "data": List<Game>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 500
* Note: 
    * pageSize = the number of games in each page
    * pageNo = the number of page required

### Delete A Game

* URL: /api/game/delete
* Method: Delete
* Parameters
    * Required: 
        * gameId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500

### Add A Game To User

* URL: /api/game/addToUser
* Method: Post
* Parameters
    * Required: 
        * gameId=[String]
        * userId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500


### Delete A Game To User

* URL: /api/game/delToUser
* Method: Delete
* Parameters
    * Required: 
        * gameId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500


### View User Game

* URL: /api/game/viewUserGame
* Method: Get
* Parameters
    * Required: 
        * userId=[String]
        * pageSize=[Integer]
        * pageNo=[Integer]
* Success Response
    * Status: 200
    * Content: 
    {\
    "data": List<Game>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 500


### Search Game With Name

* URL: /api/game/search
* Method: Get
* Parameters
    * Required: 
        * gameName=[String]
        * pageSize=[Integer]
        * pageNo=[Integer]
* Success Response
    * Status: 200
    * Content: 
    {\
    "data": List<Game>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 500
* Note:
    * This will return all game which gameName include this gameName[String]
    
### Upload Game Image

* URL: /api/game/updateImage
* Method: Post
* Parameters
    * Required: 
        * gameId=[String]
        * image=[Multipart]
* Success Response
    * Status: 200
    * Content: 1 
* Error Response
    * Status: 500

### Get Game Image

* URL: /api/game/getImage
* Method: Get
* Parameters
    * Required: 
        * gameId=[String]
* Success Response
    * Status: 200
    * Content: {Image} 
* Error Response
    * Status: 500

### Update Game Information

* URL: /api/user/updateGameInfo
* Method: PUT
* Parameters
    * Required: 
        * gameId=[String]
    * Optional:
        * gameName=[String]
        * publisher=[String]
        * description=[String]
        * artist=[String]
        * designer=[String] 
        * timePerRound=[Integer]
        * year=[Integer]
        * playerAge=[Integer]
* Success Response
    * Status: 200
    * Content: {Game}
* Error Response
    * Status: 200
    * Content: null

### Check if user own game
* URL: /api/game/checkOwn
* Method: Get
* Parameters
    * Required: 
        * userId=[String]
        * gameId=[String]
* Success Response
    * Status: 200
    * Content: 
        * 1 if user owns game,
        * 0 if not
* Error Response
    * Status: 500


## Friendship Controller

### Friendship Entity

{\
    "id": [String],\
    "userId": [ObjectId],\
    "friendId": [ObjectId],\
    "status": [Integer],\
    "requestTime": [Date],\
    "acceptTime": [Date],\
    "requestMsg": [String]\
}

### FriendRequest Entity

{\
    "id": [String],\
    "userName": [String],\
    "email": [String],\
    "avatar": [String],\
    "city": [String],\
    "gender": [Integer],\
    "age": [Integer],\
    "requestTime": [Date],\
    "requestMsg": [String]\
}

### Send a friend request

* URL: /api/friendship/sendFriendRequest
* Method: POST
* Parameters
    * Required: 
        * senderId=[String]
        * receiverId=[String]
    * Optional:
        * message=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500\
    Or
    * Status: 200
    * Content: 0

Get friend requests

* URL: /api/friendship/getFriendRequests
* Method: GET
* Parameters
    * Required: 
        * userId=[String]
        * pageNo=[Integer]
        * pageSize=[Integer]
* Success Response
    * Status: 200
    * Content:
    {\
    "data": List\<FriendRequest\>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 200
    * Content:
    {\
    "data": null,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }

### Accept a friend request

* URL: /api/friendship/acceptFriendship
* Method: POST
* Parameters
    * Required: 
        * userId=[String]
        * friendId=[String]
* Success Response
    * Status: 200
    * Content:1
* Error Response
    * Status: 500\
    Or
    * Status: 200
    * Content: 0

### Get users' friends

* URL: /api/friendship/myFriends
* Method: GET
* Parameters
    * Required: 
        * userId=[String]
        * pageNo=[Integer]
        * pageSize=[Integer]
* Success Response
    * Status: 200
    * Content:
    {\
    "data": List\<User\>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 200
    * Content:
    {\
    "data": null,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }

### Delete a friend

* URL: /api/friendship/delete
* Method: DELETE
* Parameters
    * Required: 
        * userId=[String]
        * friendId=[String]
* Success Response
    * Status: 200
    * Content:1
* Error Response
    * Status: 500\
    Or
    * Status: 200
    * Content: 0

## Club Controller

### Club Entity

{\
    "id": [String],\
    "clubName": [String],\
    "city": [String],\
    "announcement": [String],\
    "founderId": [ObjectId]\
}

### UserClub Entity

{\
    "id": [String],\
    "clubId": [ObjectId],\
    "userId": [ObjectId],\
    "joinTime": [Date]\
}

### UserClubResp Entity

{\
    "id": [String],\
    "clubName": [String],\
    "city": [String],\
    "announcement": [String],\
    "founderId": [ObjectId],\
    "joinTime": [Date]\
}

### Create a club

* URL: /api/club/create
* Method: POST
* Parameters
    * Required: 
        * clubName=[String]
        * city=[String]
        * founderId=[String]
    * Optional:
        * announcement=[String]
* Success Response
    * Status: 201
    * Content: 1
* Error Response
    * Status: 500

### Delete a club

* URL: /api/club/delete
* Method: DELETE
* Parameters
    * Required: 
        * clubId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 200
    * Content: 0\
    Or
    * Status: 500

### Join a club

* URL: /api/club/userJoinClub
* Method: POST
* Parameters
    * Required: 
        * userId=[String]
        * clubId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 200
    * Content: 0\
    Or
    * Status: 500

### Quit a club

* URL: /api/club/userQuitClub
* Method: DELETE
* Parameters
    * Required: 
        * userId=[String]
        * clubId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 200
    * Content: 0\
    Or
    * Status: 500

### Get users' clubs

* URL: /api/club/getUserClubs
* Method: GET
* Parameters
    * Required: 
        * userId=[String]
        * pageNo=[Integer]
        * pageSize=[Integer]
* Success Response
    * Status: 200
    * Content:
    {\
    "data": List\<UserClubResp\>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 200
    * Content:
    {\
    "data": null,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }

### Search clubs

* URL: /api/club/search
* Method: GET
* Parameters
    * Required: 
        * pageNo=[Integer]
        * pageSize=[Integer]
    * Optional: 
        * clubName=[String]
        * city=[String]
* Success Response
    * Status: 200
    * Content:
    {\
    "data": List\<Club\>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 500\
    Or
    * Status: 200
    * Content:
    {\
    "data": null,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }

### Get club members

* URL: /api/club/getClubMembers
* Method: GET
* Parameters
    * Required: 
        * clubId=[String]
        * pageNo=[Integer]
        * pageSize=[Integer]
* Success Response
    * Status: 200
    * Content:
    {\
    "data": List\<User\>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 200
    * Content:
    {\
    "data": null,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }

## Forum Controller

### Forum Entity

{\
    "id": [String],\
    "game": [Game],\
    "admin": [User],\
    "description": [String]\
}

### ForumPost Entity

{\
    "id": [String],\
    "title": [String],\
    "forum": [Forum],\
    "author": [User],\
    "content": [String],\
    "createTime": [Date],\
    "sticky": [boolean],\
    "numReply": [Integer],\
    "finalReplyTime": [Date]\
}

### ForumReply Entity

{\
    "id": [String],\
    "post": [ForumPost],\
    "content": [String],\
    "author": [User],\
    "replyTime": [Date],\
    "targetReply": [ForumReply]\
}

### Get forum info

* URL: /api/forum/getForumInfo
* Method: GET
* Parameters
    * Required: 
        * gameId=[String]
* Success Response
    * Status: 200
    * Content: {Forum}
* Error Response
    * Status: 200
    * Content: null

### Get posts

* URL: /api/forum/getPosts
* Method: GET
* Parameters
    * Required: 
        * forumId=[String]
        * pageNo=[Integer]
        * pageSize=[Integer]
* Success Response
    * Status: 200
    * Content:
    {\
    "data": List\<ForumPost\>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 200
    * Content:
    {\
    "data": null,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Note
    * The return list is ordered.

### Create a post

* URL: /api/forum/createPost
* Method: POST
* Parameters
    * Required: 
        * title=[String]
        * forumId=[String]
        * authorId=[String]
    * Optional:
        * content=[String]
        * sticky=[boolean]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500
* Note
    * Only the forum member can create a post.
    * Only the forum administrator can create a sticky post.

### Delete a post

* URL: /api/forum/deletePost
* Method: DELETE
* Parameters
    * Required: 
        * postId=[String]
        * userId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500
* Note
    * Only the post author and forum administrator can delete a post.

### Get replies

* URL: /api/forum/getReplies
* Method: GET
* Parameters
    * Required: 
        * postId=[String]
        * pageNo=[Integer]
        * pageSize=[Integer]
* Success Response
    * Status: 200
    * Content:
    {\
    "data": List\<ForumReply\>,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Error Response
    * Status: 200
    * Content:
    {\
    "data": null,\
    "pageSize": [Integer],\
    "pageNo": [Integer],\
    "totalPage": [Integer]\
    }
* Note
    * The return list is ordered.

### Add a reply

* URL: /api/forum/addReply
* Method: POST
* Parameters
    * Required: 
        * postId=[String]
        * authorId=[String]
        * content=[String]
    * Optional:
        * targetReplyId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500
* Note
    * Only the forum member can add a reply.
    * If responding to another reply, post the replyId in targetReplyId.

### Delete a reply

* URL: /api/forum/deleteReply
* Method: DELETE
* Parameters
    * Required: 
        * replyId=[String]
        * userId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500
* Note
    * Only the reply author or forum administrator can delete a reply.

### Stick a post

* URL: /api/forum/stickPost
* Method: POST
* Parameters
    * Required: 
        * postId=[String]
        * userId=[String]
        * stick=[boolean]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500
* Note
    * Only the forum administrator can stick a post.
    * Set stick to true to stick the post, otherwise to unstick the post.

### Join a forum

* URL: /api/forum/userJoinForum
* Method: POST
* Parameters
    * Required: 
        * forumId=[String]
        * userId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500\
    Or
    * Status: 200
    * Content: 0

### Quit a forum

* URL: /api/forum/userQuitForum
* Method: DELETE
* Parameters
    * Required: 
        * forumId=[String]
        * userId=[String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500\
    Or
    * Status: 200
    * Content: 0
* Note
    * The administrator cannot quit the forum.

### Forum member check

* URL: /api/forum/isForumMember
* Method: GET
* Parameters
    * Required: 
        * forumId=[String]
        * userId=[String]
* Success Response
    * Status: 200
    * Content: true or false
* Error Response
    * Status: 500

## Sell Controller

### Sell Entity

{\
    "id": [String],\
    "seller": [User],\
    "game": [Game],\
    "contact": [String],\
    "price": [Double],\
    "description": [String],\
    "postTime": [Date]\
}

### Post a Sell

* URL: /api/sell/post
* Method: Post
* RequestBody:\
        {\
            "sellerId" = [String],\
            "gameId" = [String],\
            "contact" = [String],\
            "price" = [Double],\
            "description" = [String]\
        }
* Success Response
    * Status: 200
    * Content: [Sell]
* Error Response
    * Status: 500

### Delete a Sell
* URL: /api/sell/delete
* Method: Delete
* Parameters
    * Required: 
        sellId = [String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500

### Update a Sell
* URL: /api/sell/update
* Method: Put
* Parameters
    * Required: 
       * sellId = [String]
    * Optional:
        * contact = [String]
        * price = [Double]
        * description = [String]
* Success Response
    * Status: 200
    * Content: [Sell]
* Error Response
    * Status: 500

### View a Game's Sell
* URL: /api/sell/viewGameSell
* Method: Get
* Parameters
    * Required: 
       * gameId = [String]
       * pageSize = [Integer]
       * pageNo = [Integer]
* Success Response
    * Status: 200
    * Content: [Page<Sell>]
* Error Response
    * Status: 500

### View a User's Sell
* URL: /api/sell/viewUserSell
* Method: Get
* Parameters
    * Required: 
       * userId = [String]
       * pageSize = [Integer]
       * pageNo = [Integer]
* Success Response
    * Status: 200
    * Content: [Page<Sell>]
* Error Response
    * Status: 500

### View a Sell
* URL: /api/sell/viewSell
* Method: Get
* Parameters
    * Required: 
       * sellId = [String]
* Success Response
    * Status: 200
    * Content: [Sell]
* Error Response
    * Status: 500

## Review Controller

### Review Entity

{\
    "id": [String],\
    "reviewer": [User],\
    "game": [Game],\
    "content": [String],\
    "score": [Integer],\
    "postTime": [Date]\
    "anonymous": [Integer](0 False, 1 True)\
}

Note: if anonymous is True, the reviewer will return null.

### Post a Review

* URL: /api/review/post
* Method: Post
*  RequestBody:\
        {\
        reviewerId = [String],\
        gameId = [String],\
        content = [String],\
        score = [Integer],\
        anonymous = [Integer]\
        }
* Success Response
    * Status: 200
    * Content: [Review]
* Error Response
    * Status: 500

### Delete a Review
* URL: /api/review/delete
* Method: Delete
* Parameters
    * Required: 
        reviewId = [String]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500

### Update a Review
* URL: /api/review/update
* Method: Put
* Parameters
    * Required: 
       * reviewId = [String]
    * Optional:
        * content = [String]
        * score = [Integer]
        * anonymous = [Integer]
* Success Response
    * Status: 200
    * Content: [Review]
* Error Response
    * Status: 500

### View a Game's Review
* URL: /api/review/viewGameReview
* Method: Get
* Parameters
    * Required: 
       * gameId = [String]
       * pageSize = [Integer]
       * pageNo = [Integer]
* Success Response
    * Status: 200
    * Content: [Page<Review>]
* Error Response
    * Status: 500

### View a User's Reveiw
* URL: /api/review/viewUserReview
* Method: Get
* Parameters
    * Required: 
       * userId = [String]
       * pageSize = [Integer]
       * pageNo = [Integer]
* Success Response
    * Status: 200
    * Content: [Page<Review>]
* Error Response
    * Status: 500

### View a Review
* URL: /api/review/viewReview
* Method: Get
* Parameters
    * Required: 
       * reviewId = [String]
* Success Response
    * Status: 200
    * Content: [Review]
* Error Response
    * Status: 500

### Check if user has a Review for a game
* URL: /api/review/checkExist
* Method: Get
* Parameters
    * Required: 
       * gameId = [String]
       * userId = [String]
* Success Response
    * Status: 200
    * Content: 
        * 1 if exists,
        * 0 if not 
* Error Response
    * Status: 500


