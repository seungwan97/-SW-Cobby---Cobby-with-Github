import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import LoginComponents from "@/components/page/LoginPage/LoginPage";

export default function Home() {
  return (
    <Fragment>
      <page.PageWrapper>
        <LoginComponents />
      </page.PageWrapper>
    </Fragment>
  );
}
