import { Helper } from "../helpers/helper.js";
import { ProductService } from "../products/product-service.js";


export class NPLModel {
    #nlp = null;
    #dock = null

    constructor(dock) {
        this.#dock = dock
        this.#nlp = dock.get('nlp');
    }

    get nlp() {
        return this.#nlp
    }

    async train() {
        this.#nlp.registerActionFunction('handleAddProduct', this.addProduct);
        this.#nlp.registerActionFunction('handleRemoveProduct', this.removeProduct);
        this.#nlp.registerActionFunction('handleProductExist', this.productExist);

        // Train the model.
        const isSuccess = await this.#nlp.train();
        await this.#nlp.save();

        if (isSuccess) {
            return this.#nlp;
        }
        return isSuccess
    }

    async addProduct(data) {
        let product = data.entities[0]?.sourceText
        let quantity = 1

        if (Helper.containsNumbers(product)) {
            ({ quantity, product } = Helper.extractQuantity(product))
        }

        const isSuccess = new ProductService().add(product, 1);

        if (isSuccess)
            data.answer = `J'ai bien ajouté ${quantity ?? ""} ${product} au stock`;
        else
            data.answer = `Je n'ai pas pu ajouter ${quantity ?? ""} ${product} au stock, une erreur est survenue`;

        return data
    }

    async removeProduct(data) {
        let product = data.entities[0]?.sourceText;
        let quantity = 1
        let isSuccess = false

        if (Helper.containsNumbers(product)) {
            ({ quantity, product } = Helper.extractQuantity(product))
        }

        isSuccess = new ProductService().remove(product, quantity);

        if (product && isSuccess) {
            data.answer = `J'ai bien retiré ${quantity} ${product} du stock`;
        }
        else {
            data.answer = `Je n'ai pas réussit a retirer les ${product} du stock`;
        }

        return data
    }

    async productExist(data) {
        const product = data.entities[0]?.sourceText;
        const quantity = await new ProductService().exist(product);

        console.log(data.entities);
        if (!data.entities || data.entities.length === 0 || !product) {
            data.answer = "Veuillez reformuler, je n'ai pas compris cette phrase"
            return data
        }

        if (quantity < 1)
            data.answer = "Vous n'avez plus de " + product
        else
            data.answer = "Vous avez " + quantity + " " + product + " dans votre stock"
        return data
    }
}