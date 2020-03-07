# API

**website**

http://www.cog.codes

**Port**

5010

#### User Controller
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
    * Content: 1
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
    * Status: 500

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
    * Content: null

    Or

    * Status: 500

**Get User Avatar**

* URL: /api/user/getAvatar
* Method: GET
* Parameters
    * Required: 
        * filename=[String]
* Success Response
    * Status: 200
    * Content: {Image}
* Error Response
    * Status: 500