/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{html,js}', "../templates/**/*.html"],
  // content: ['./src/**/*.{html,js}'],
  theme: {
    extend: {}
  },

  plugins: [require("daisyui")]
};