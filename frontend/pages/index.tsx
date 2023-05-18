import { Fragment, useEffect, useState } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import LoginComponents from "@/components/page/LoginPage/LoginPage";
import { useRouter } from "next/router";
<<<<<<< HEAD
=======
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

export default function Home() {
  return (
    <Fragment>
      <page.PageWrapper>
        <LoginComponents />
      </page.PageWrapper>
    </Fragment>
  );
}
