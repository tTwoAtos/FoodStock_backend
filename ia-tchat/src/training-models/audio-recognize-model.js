import * as speechCommands from '@tensorflow-models/speech-commands';

export class AudioRecognizeModel {
    #recognizer = null
    constructor() {
        init()
    }

    async init() {
        this.#recognizer = speechCommands.create('BROWSER_FFT');
        await this.#recognizer.ensureModelLoaded();
    }

    async train() {
        const transferRecognizer = this.#recognizer.createTransfer('mywords');
        await transferRecognizer.collectExample('Ajoute un produit', 1);
        await transferRecognizer.collectExample('Retire un produit', 2);
        await transferRecognizer.collectExample("J'ai le produit ?", 3);
        await transferRecognizer.train({ epochs: 10 });

        return this.#recognizer
    }
}