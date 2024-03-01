export class Helper {
    static containsNumbers(str) {
        return /[0-9]/.test(str);
    }

    static containsOnlyNumbers(str) {
        return /^\d+$/.test(str);
    }

    static extractQuantity(string) {
        const words = string.split(" ")
        let quantity = null
        let product = ""

        for (const word of words) {
            if (Helper.containsOnlyNumbers(word))
                quantity = word
            else
                product += word + " "
        }

        product = product.slice(0, -1) // remove last char, witch is a space

        return {
            quantity,
            product
        }
    }
}