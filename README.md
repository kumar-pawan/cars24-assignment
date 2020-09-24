# cars24-assignment

This service provide the user to get the list of running auctions and place a bid if they wanted to buy some item from the auction.

This README.md wil help you to know about tech stack used in this service and how to setup on your local and use.

# Tech Stack
1. java8
2. Springboot: 2.3.4.RELEASE (One of the top framework in the web development world and very easy to use. Also, comes up with ready to use component for almost all the use cases)
3. H2 Database (JPA):(For assignment purpose. Although, JPA is used, we can change to any relationtional database by just changes few configurations)
4. Redis: for user session and caching

#prerequisite for running on local
This Service depends on Redis for session menagement. So you need to have a redis servie running on your local. or you can just update the application-X.properties files to point it to the redis service on your machine.

#baseUrl
http://localhost:8080
This service consist of two rest endpoints 1. Get all running auctions. 2. Place a bid

#headers
only a logged in user can access the endpoints. For the purpose user need to pass a accessToken in the headers.
 key: accessToken
# few dummy values for access token ready to use
 sdlkfahqwo874y3874yf832ghf87234gf32
 lksjdoighjrgownb3498h03gnrdsjknvf32
 kjwhef923y4rwehfe9238f2hfweofh23kbdw


# Rest Endpoints
 1. Get Auctions:  /cars24/auctions?status=running
 2. Place a bid: /cars24/auctions/{itemCode}/bid

# Pagination
For Auction api, Pagination is enabled, following request parameter you can pass for Pagination
1. page(int) -- page number
2. size(int) -- number of item in the page
3. sort (filedName,ASC) -- name of the filed received in the response

 you can refer to the postman collection file for request and response models and further details
 /Cars24Assignment.postman_collection.json

# H2 DB
We have used H2 db for assignment purpose. URI for h2 console is:
/h2-console

For getting the details about the sample data and table schema please refer data.sql file under the resources


# Dockerization
You can build a docker image of your service by simply running below command
  mvn spring-boot:buid-image

  Use below command to run the docker image
  docker run -d -p 8080:8080 --name car24-auction -e "SPRING_REDIS_HOST=<your_machine_IP>" -t assignment:0.0.1-SNAPSHOT
