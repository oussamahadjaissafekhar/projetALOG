FROM node

# Set the working directory
WORKDIR /Implementation

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install knex
RUN npm install mysql
# Copy the application code to the working directory
COPY . .
# Start the application
CMD node userManagementService.js
