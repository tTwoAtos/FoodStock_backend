/**
 * Entry point
 * @version 1.0.0
 * - App loading 
 * @author Aélion-2024-02 <s35395@stagiaire.aelion.fr>
*/

import { dockStart } from "@nlpjs/basic";
import { NPLModel } from "./src/training-models/nlp-model.js";

import dotenv from 'dotenv';
//@ts-ignore
import express from "express";
import { registerWithEureka } from "./src/helpers/eureka-helper.js";

export class Main {
    #app = null
    #nlp = null

    constructor() {
        this.#bootstrap()
    }

    async #bootstrap() {
        dotenv.config();

        this.startExpressServer()

        this.trainModel()

        this.setRoutes()

        registerWithEureka('BOT-SERVICE', process.env.SERVER_PORT);
    }

    setRoutes() {
        this.#app.post('/bot/message', async (req, res) => {
            const result = await this.#nlp.process('fr', req.body?.message)
            res.send(result?.answers)
        })
    }

    startExpressServer() {
        this.#app = express();
        this.#app.use(express.json());

        this.#app.listen(process.env.SERVER_PORT, () => {
            console.log(`[server]: Server is running at http://localhost:${process.env.SERVER_PORT}`);
        });
    }

    async trainModel() {
        const dock = await dockStart();
        const model = new NPLModel(dock)
        await model.train()
        this.#nlp = model.nlp
    }
}

(
    () => {
        const app = new Main()
    }
)() // Autocalled JS function !