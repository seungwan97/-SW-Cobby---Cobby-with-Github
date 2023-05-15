import { Fragment, useEffect, useState } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import LoginComponents from "@/components/page/LoginPage/LoginPage";
import { useRouter } from "next/router";

export default function Home() {
  return (
    <Fragment>
      <page.PageWrapper>
        <LoginComponents />
      </page.PageWrapper>
    </Fragment>
  );
}
