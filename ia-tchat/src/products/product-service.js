export class ProductService {
    async add(product, quantity) {
        console.log(quantity + " " + product + " ajoutée");

        const isRandom = Math.random() < 0.1;

        return isRandom
    }

    async remove(product, quantity = 0) {
        if (quantity === 0)
            console.log("Tous les " + product + " ont été retirée du stock");
        console.log(quantity + " " + product + " ont été retirée du stock");

        const isRandom = Math.random() < 0.1;

        return isRandom
    }

    async exist(product) {
        const isRandom = Math.random() < 0.5;
        const quantity = Math.floor(Math.random() * 5);

        if (isRandom)
            console.log("vous n'avez plus de " + product);
        else
            console.log("Vous avez " + quantity + " " + product + " dans votre stock");

        return quantity;

    }
}