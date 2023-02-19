# BerkayTutalAndela

## How to Run

1. Navigate to the project directory on your machine
2. Ensure you have `run.sh` file at the root of the directory
3. Ensure you have installed **Docker** and **docker-compose** in your system.
4. Open your CMD, and run this command:  `./run.sh`


## How to Test

1. Ensure you have installed **Postman** in your system
2. Run **Postman** app
3. Import `Andela.postman_collection.json` to your workspace
4. Before running **Upload New Epaper** request, select an example request by clicking **Select Files** button in the value of key **file**
5. Select an example file from `requests` folder in the project root.

> If you want to test it using newman, you need to change path of example request file location in `Andela.postman_collection.json`.
> This is  necessary since Postman doesn't allow usage of relative paths in file locations.

## Filtering
You can filter **dpi**, **width**, **height**, **fileName**, **newspaperName**, **uploadTime**  
If you use only **one** parameter on **dpi**, **width** a **height**, it will filter it as `equals`.  
If you use **two** parameters on **dpi**, **width** a **height**, it will filter it as `between`.

You can use only one parameter on **fileName** and **newspaperName** to filter it as `contains`.

You can use only one parameter on **uploadTime** to filter it as `greater than or equal`.


## Time spent

Project took about 4 hours to finish from scratch
