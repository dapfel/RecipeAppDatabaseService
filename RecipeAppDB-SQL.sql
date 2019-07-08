DROP TABLE UserCuisines;
DROP TABLE Follows;
DROP TABLE RecipeCuisines;
DROP TABLE RecipePictures;
DROP TABLE RecipeIngredients;
DROP TABLE RecipeInstructions;
DROP TABLE RecipeComments;
DROP TABLE Recipes;
DROP TABLE UserProfiles;

CREATE TABLE UserProfiles (
   email varchar (320),
   password varchar (30),
   firstName varchar (20),
   lastName varchar (30),
   country varchar (30),
   skillLevel varchar (20),
   PRIMARY KEY (email)
);

CREATE TABLE UserCuisines (
   email varchar (320),
   cuisine varchar (20),
   PRIMARY KEY (email, cuisine),
   FOREIGN KEY (email) REFERENCES UserProfiles (email)
);

CREATE TABLE Follows (
   followedEmail varchar (320),
   followerEmail varchar (320),
   FOREIGN KEY (followedEmail) REFERENCES UserProfiles (email),
   FOREIGN KEY (followerEmail) REFERENCES UserProfiles (email)
);

CREATE TABLE Recipes (
   recipeID INT GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
   name varchar (40),
   type varchar (20),
   author varchar (320),
   skillLevel varchar (20),
   releaseDate DATE,
   PRIMARY KEY (recipeID),
   FOREIGN KEY (author) REFERENCES UserProfiles (email)
);

CREATE TABLE RecipeCuisines (
   recipeID INT,
   cuisine varchar (20),
   PRIMARY KEY (recipeID, cuisine),
   FOREIGN KEY (recipeID) REFERENCES Recipes (recipeID)
);

CREATE TABLE RecipePictures (
   recipeID INT,
   pictureNum INT,
   picture BLOB (64000),
   PRIMARY KEY (recipeID, pictureNum),
   FOREIGN KEY (recipeID) REFERENCES Recipes (recipeID)
);

CREATE TABLE RecipeIngredients (
   recipeID INT,
   ingredient varchar (30),
   quantity varchar (30),
   PRIMARY KEY (recipeID, ingredient),
   FOREIGN KEY (recipeID) REFERENCES Recipes (recipeID)
);

CREATE TABLE RecipeInstructions (
   recipeID INT,
   instructionNum INT,
   instruction varchar (400),
   PRIMARY KEY (recipeID, instructionNum),
   FOREIGN KEY (recipeID) REFERENCES Recipes (recipeID)
);

CREATE TABLE RecipeComments (
   recipeID INT,
   commentNum INT,
   commentAuthor varchar (320),
   CommentText varchar (1000),
   PRIMARY KEY (recipeID, commentNum),
   FOREIGN KEY (recipeID) REFERENCES Recipes (recipeID),
   FOREIGN KEY (commentAuthor) REFERENCES UserProfiles (email)
);
