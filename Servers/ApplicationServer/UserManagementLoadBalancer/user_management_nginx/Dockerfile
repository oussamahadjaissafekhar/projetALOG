FROM nginx
# Install vim
RUN apt-get update && apt-get install -y vim

# Install nano
RUN apt-get update && apt-get install -y nano

COPY nginx.conf /etc/nginx/conf.d/default.conf