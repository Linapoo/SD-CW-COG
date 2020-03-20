# API

**website**

http://www.cog.codes

## User Controller

**User Entity**

{\
    "id": [String],\
    "userName": [String],\
    "email": [String],\
    "avatar": [String],\
    "city": [String],\
    "gender": [Integer],\
    "age": [Integer]\
}

**Register**

* URL: /api/user/register
* Method: POST
* Parameters
    * Required: 
        * userName=[String]
        * password=[String]
    * Optional:
        * email=[String]
        * city=[String]
        * gender=[Integer] (0 male, 1 female)
        * age=[Integer]
* Success Response
    * Status: 200
    * Content: {User}
* Error Response
    * Status: 500

**Login**

* URL: /api/user/login
* Method: POST
* Parameters
    * Required: 
        * userName=[String]
        * password=[String]
* Success Response
    * Status: 200
    * Content: {User}
* Error Response
    * Status: 200
    * Content: null

**Update User Information**

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

**Reset Password**

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

**Update User Avatar**

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

**Get User Avatar**

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

**Game Entity**

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

**Game Publish**

* URL: /api/game/publish
* Method: POST
* Parameters
    * Required: 
        * gameName=[String]
        * publisher=[String]
    * Optional:
        * description=[String]
        * artist=[String]
        * designer=[String] 
        * timePerRound=[Integer]
        * year=[Integer]
        * playerAge=[Integer]
* Success Response
    * Status: 200
    * Content: 1
* Error Response
    * Status: 500

**View A Game**

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

**View All Game**

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

**Delete A Game**

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

**Add A Game To User**

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
* Note: 
    * call with same user and game twice will still get Success Response(will be fixed)

**Delete A Game To User**

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
* Note: 
    * call with same user and game twice will still get Success Response(will be fixed)

**View User Game**

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

**Search Game With Name**

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
    
**Upload Game Image**

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

**Get Game Image**

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

**Update Game Information**

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

## Friendship Controller

**Friendship Entity**

{\
    "id": [String],\
    "userId": [ObjectId],\
    "friendId": [ObjectId],\
    "status": [Integer],\
    "requestTime": [Date],\
    "acceptTime": [Date],\
    "requestMsg": [String]\
}

**FriendRequest Entity**

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

**Send a friend request**

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

**Get friend requests**

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

**Accept a friend request**

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

**Get users' friends**

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

**Delete a friend**

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






