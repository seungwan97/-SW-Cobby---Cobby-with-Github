import { GlobalStyle } from "@/styles/global-style";
import type { AppProps } from "next/app";
import { config } from "@fortawesome/fontawesome-svg-core";
import "@fortawesome/fontawesome-svg-core/styles.css";
import Head from "next/head";
import Link from "next/link";
import cookies from "next-cookies";
config.autoAddCss = false;

export default function App({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
<<<<<<< HEAD
=======
        <title>Cobby - Github with Cobby!</title>
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        <meta name="viewport" content="width=device-width, initial-scale=1" />
      </Head>
      <GlobalStyle />
      <Component {...pageProps} />
    </>
  );
}
