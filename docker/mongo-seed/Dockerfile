FROM mongo

COPY init.json /init.json
CMD mongoimport --username movie --password movie --host mongo --db admin --collection movie --type json --file /init.json --jsonArray
