class CreditCard extends HTMLElement {
  constructor() {
    super();
    const shadow = this.attachShadow({ mode: 'open' });

    shadow.innerHTML = /* css */`
    <style>
      :host {
        display: block;
        font-family: "Arial", sans-serif;
        width: 360px;
        height: 220px;
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
        border-radius: 12px;
        overflow: hidden;
      }

      .container {
        box-sizing: border-box;
        background: linear-gradient(168deg, hsl(var(--dwc-color-primary-h), var(--dwc-color-primary-s), 25%), hsl(var(--dwc-color-primary-h), var(--dwc-color-primary-s), 10%)), url(https://www.transparenttextures.com/patterns/cartographer.png);
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-blend-mode: lighten;
        width: 100%;
        height: 100%;
        padding: 16px;
        color: #fff;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        border-radius: 12px;
      }

      header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 16px;
      }

      .logo img {
        width: 48px;
        height: auto;
        filter: brightness(0) invert(1);
      }

      .chip {
        width: 48px;
        height: 32px;
        background: no-repeat center/contain url("/static/images/chip.png");
      }

      .card-details {
        display: flex;
        flex-direction: column;
        gap: 8px;
      }

      .card-details .number {
        font-size: 18px;
        font-weight: bold;
        letter-spacing: 1.5px;
        margin: 0;
        text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.7);
      }

      .card-details .name {
        font-size: 14px;
        font-weight: normal;
        letter-spacing: 1px;
        margin: 0;
        text-transform: uppercase;
        text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.7);
      }

      .valid-date {
        text-align: right;
      }

      .valid-date .label {
        margin: 0;
        font-size: 12px;
        font-weight: normal;
        letter-spacing: 1px;
        color: #ccc;
      }

      .valid-date .value {
        margin: 0;
        font-size: 14px;
        font-weight: bold;
        color: #fff;
        text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.7);
      }

      @media (max-width: 480px) {
        :host {
          width: 300px;
          height: 180px;
        }

        .logo img {
          width: 40px;
        }

        .chip {
          width: 40px;
          height: 28px;
        }

        .card-details .number {
          font-size: 16px;
        }

        .card-details .name {
          font-size: 12px;
        }

        .valid-date .label {
          font-size: 10px;
        }

        .valid-date .value {
          font-size: 12px;
        }
      }
    </style>

    <div class="container">
      <header>
        <div class="logo">
          <img src="/static/images/visa.png" alt="Visa Logo" />
        </div>
        <div class="chip"></div>
      </header>

      <div class="card-details">
        <div class="number">${this.getAttribute('card-number') || '#### #### #### ####'}</div>
        <div class="name">${this.getAttribute('card-holder') || 'Card Holder'}</div>
        <div class="valid-date">
          <div class="label">Valid Thru</div>
          <div class="value">${this.getAttribute('card-expiration') || 'MM/YY'}</div>
        </div>
      </div>
    </div>
    `;
  }

  static get observedAttributes() {
    return ['card-number', 'card-holder', 'valid-date'];
  }

  attributeChangedCallback(name, oldValue, newValue) {
    if (oldValue !== newValue) {
      if (name === 'card-number') {
        this.shadowRoot.querySelector('.number').textContent = newValue;
      } else if (name === 'card-holder') {
        this.shadowRoot.querySelector('.name').textContent = newValue;
      } else if (name === 'valid-date') {
        this.shadowRoot.querySelector('.valid-date .value').textContent = newValue;
      }
    }
  }
}

customElements.define('credit-card', CreditCard);
