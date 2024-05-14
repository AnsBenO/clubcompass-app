/** @type {import('tailwindcss').Config} */
module.exports = {
	content: ["src/main/resources/templates/**/*.html"],
	theme: {
		fontFamily: {},
		extend: {
			fontFamily: {
				notoSerif: ["Noto Serif", "serif"],
				noto: ["Noto Sans", "sans-serif"],
				rubik: ["Rubik", "sans-serif"],
			},
		},
	},
	plugins: [],
};
