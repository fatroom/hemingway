This is list of prepared curls for performing different tasks with service

* Find all authors


    curl http://localhost:8080/authors

* Create author


    curl -XPOST http://localhost:8080/authors \
      -u editor:passw0rd \
      -H 'Content-Type: application/json' \
      -d '{ "name": "Jack Kerouac"}'

* Find articles published by author


    curl http://localhost:8080/authors/1/articles

* Find all articles


    curl http://localhost:8080/articles

* Publish article


    curl -XPOST http://localhost:8080/articles \
      -u editor:passw0rd \
      -H 'Content-Type: application/json' \
      -d '{
            "header": "On the Road",
            "shortDescription": "The best teacher is experience",
            "text": "The only people for me are the mad ones, the ones who are mad to live, mad to talk, mad to be saved...",
            "publishDate": "1957-09-05",
            "keywords":["book", "beat"],
            "authors":["http://localhost:8080/authors/1"]
          }'

* Update article


    curl -XPUT http://localhost:8080/articles/7 \
      -u editor:passw0rd \
      -H 'Content-Type: application/json' \
      -d '{
            "header": "On the Road",
            "shortDescription": "The best teacher is experience",
            "text": "The only people for me are the mad ones, the ones who are mad to live, mad to talk, mad to be saved...",
            "publishDate": "1957-09-05",
            "keywords":["book", "beat"],
            "authors":["http://localhost:8080/authors/5"]
          }'


* Add additional authors to article


    curl -v -X PATCH -H http://localhost:8080/articles/7/authors
      -u editor:passw0rd \
      -H 'Content-Type: text/uri-list' \
      -d 'http://localhost:8080/authors/2"
