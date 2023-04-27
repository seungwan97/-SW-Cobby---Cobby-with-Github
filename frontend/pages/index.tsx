import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import LoginPage from "@/components/page/LoginPage/LoginPage";

export default function Home() {
  return (
    <Fragment>
      <page.PageWrapper>
        <LoginPage />
      </page.PageWrapper>
    </Fragment>
  );
}
