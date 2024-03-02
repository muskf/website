<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <link id="favicon" rel="icon" href="https://cdn.jsdelivr.net/gh/CubeWhyMC/celestial/src/main/resources/images/icons/icon-light.png" type="image/png">
  <title data-i18n="title">Celestial</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v6.0.0/css/all.css" />
  <!-- Google Fonts Roboto -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
  <!-- MDB -->
  <link rel="stylesheet" href="/css/mdb.min.css" />
  <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <!-- alert -->
  <link rel="stylesheet" type="text/css" href="/css/sweetalert.css">
  <script type="text/javascript" src="/js/sweetalert.js"></script>
  <!-- icon -->
  <script src="/js/theme.js"></script>
  <!-- lenis -->
  <script src="https://unpkg.com/@studio-freight/lenis/dist/lenis.min.js"></script>
  <script src="/js/lenis.js"></script>
</head>
<body data-lenis-prevent-touch>
<!--Main Navigation-->
<header>
  <style>
    #intro {
      /*fuck idea, please ignore this error*/
      background-image: url("/images/galaxy-night-view.jpg");
      height: 100vh;
    }

    /* Height for devices larger than 576px */
    @media (min-width: 992px) {
      #intro {
        margin-top: -58.59px;
      }
    }

    .navbar .nav-link {
      color: #fff !important;
    }

    html.lenis {
      height: auto;
    }

    。lenis。lenis-smooth {
      scroll-behavior: auto;
    }

    。lenis。lenis-smooth [data-lenis-prevent] {
      overscroll-behavior: contain;
    }

    。lenis。lenis-stopped {
      overflow: hidden;
    }

    。lenis。lenis-scrolling iframe {
      pointer-events: none;
    }
  </style>

  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-dark d-none d-lg-block" style="z-index: 2000;">
    <div class="container-fluid">
      <!-- Navbar brand -->
      <a class="navbar-brand nav-link" href="#">
        <strong>Celestial</strong>
      </a>
      <button class="navbar-toggler" type="button" data-mdb-collapse-init data-mdb-target="#navbarExample01"
              aria-controls="navbarExample01" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarExample01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a class="nav-link" aria-current="page" href="#intro" data-i18n="nav-home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/download" rel="nofollow"
               data-i18n="nav-download">Download</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/donate" rel="nofollow"
               data-i18n="nav-donate">Donate</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="https://mc.lunarclient.top/docs/" target="_blank" data-i18n="nav-doc">Docs</a>
          </li>
        </ul>

        <ul class="navbar-nav d-flex flex-row">
          <!-- Icons -->
          <li class="nav-item me-3 me-lg-0">
            <a class="nav-link" href="https://space.bilibili.com/1106744676" rel="nofollow"
               target="_blank">
              <i class="fa-brands fa-bilibili"></i>
            </a>
          </li>
          <li class="nav-item me-3 me-lg-0">
            <a class="nav-link" href="https://github.com/CubeWhyMC" rel="nofollow" target="_blank">
              <i class="fab fa-github"></i>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- Navbar -->

  <!-- Background image -->
  <div id="intro" class="bg-image shadow-2-strong">
    <div class="mask" style="background-color: rgba(0, 0, 0, 0.8);">
      <div class="container d-flex align-items-center justify-content-center text-center h-100">
        <div class="text-white" data-mdb-theme="dark">
          <h1 class="mb-3" data-i18n="poop1">Celestial—Your next Minecraft launcher</h1>
          <h5 class="mb-4" data-i18n="poop2">Open Source & Free</h5>
          <a class="btn btn-outline-light btn-lg m-2" data-mdb-ripple-init href="#"
             role="button" data-i18n="btn-download" onclick="window.open('/download')">Download</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Background image -->
</header>
<!--Main Navigation-->

<!--Main layout-->
<main class="mt-5">
  <div class="container" >
    <!--Section: Content-->
    <section>
      <div class="row" >
        <div class="col-md-6 gx-5 mb-4">
          <div class="bg-image hover-overlay shadow-2-strong" data-mdb-ripple-init data-mdb-ripple-color="light">
            <img src="/images/launcher.png" class="img-fluid" />
            <a href="#!">
              <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
            </a>
          </div>
        </div>

        <div class="col-md-6 gx-5 mb-4" >
          <h4><strong data-i18n="about-title">About Celestial</strong></h4>
          <p class="text-muted" data-i18n="about-content1">
          </p>
          <p><strong data-i18n="about-features"></strong></p>
          <p class="text-muted" data-i18n="about-features-content">
          </p>
        </div>
      </div>
    </section>
    <!--Section: Content-->

    <hr class="my-5" />

    <!--Section: Content-->
    <section class="text-center">
      <h4 class="mb-5"><strong data-i18n="launcher-case-title"></strong></h4>

      <div class="row" >
        <div class="col-lg-4 col-md-12 mb-4" >
          <div class="card" >
            <div class="bg-image hover-overlay" data-mdb-ripple-init data-mdb-ripple-color="light" >
              <img src="/images/launch.png" class="img-fluid" />
              <a href="#!">
                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
              </a>
            </div>
            <div class="card-body" >
              <h5 class="card-title" data-i18n="case-title1">Tidy page</h5>
              <p class="card-text" data-i18n="case-content1">
                <!-- content1 -->
              </p>
              <a href="#!" class="btn btn-primary scrollToTopButton" data-mdb-ripple-init data-i18n="case-download">DOWNLOAD</a>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 mb-4" >
          <div class="card" >
            <div class="bg-image hover-overlay" data-mdb-ripple-init data-mdb-ripple-color="light">
              <img src="/images/size.png" class="img-fluid" />
              <a href="#!">
                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
              </a>
            </div>
            <div class="card-body" >
              <h5 class="card-title" data-i18n="case-title2">Lightweight size</h5>
              <p class="card-text" data-i18n="case-content2">
                <!-- content2 -->
              </p>
              <a href="#!" class="btn btn-primary scrollToTopButton" data-mdb-ripple-init data-i18n="case-download">DOWNLOAD</a>
            </div>
          </div>
        </div>

        <div class="col-lg-4 col-md-6 mb-4" >
          <div class="card" >
            <div class="bg-image hover-overlay" data-mdb-ripple-init data-mdb-ripple-color="light" >
              <img src="/images/support.png" class="img-fluid" />
              <a href="#!">
                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);" ></div>
              </a>
            </div>
            <div class="card-body" >
              <h5 class="card-title" data-i18n="case-title3">Plentiful Support</h5>
              <p class="card-text" data-i18n="case-content3">
                <!-- content3 -->
              </p>
              <a href="https://discord.gg/rCqCepgWJc" class="btn btn-primary" data-mdb-ripple-init data-i18n="case-join">DOWNLOAD</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--Section: Content-->
  </div>
</main>
<!--Main layout-->

<!-- Footer -->
<footer class="bg-body-tertiary text-center" >
  <!-- Grid container -->
  <div class="container p-4" >
    <!-- Section: Social media -->
    <section class="mb-4">
      <!-- Github -->
      <a data-mdb-ripple-init class="btn btn-outline btn-floating m-1" href="https://github.com/CubeWhyMC" role="button" target="_blank"
      ><i class="fab fa-github"></i
        ></a>
    </section>
    <!-- Section: Social media -->

    <!-- Section: Form -->

    <!-- Section: Text -->
    <section class="mb-4" >
      <p data-i18n="footer-ads">
        We are looking for ads partners,contact us at me@lunarclient.top for more information.
      </p>
    </section>
    <!-- Section: Text -->

    <!-- Section: Links -->
    <section class="" >
      <!--Grid row-->
      <div class="row" >
        <!--Grid column-->
        <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
          <h5 class="text-uppercase" data-i18n="footer-links">Links</h5>

          <ul class="list-unstyled mb-0">
            <li>
              <a class="text-body" href="https://github.com/CubeWhyMC/celestial" data-i18n="footer-repo">GitHub Repo</a><br>
              <a class="text-body" href="/donate" data-i18n="footer-donate">Donate us</a><br>
              <a class="text-body" href="/liquid">LiquidLunar</a>
            </li>
            <!--             <li>
                          <a class="text-body" href="#!">Link 2</a>
                        </li>
                        <li>
                          <a class="text-body" href="#!">Link 3</a>
                        </li>
                        <li>
                          <a class="text-body" href="#!">Link 4</a>
                        </li> -->
          </ul>
        </div>
        <!--Grid column-->

        <!--
                <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                  <h5 class="text-uppercase">Links</h5>

                  <ul class="list-unstyled mb-0">
                    <li>
                      <a class="text-body" href="#!">Link 1</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 2</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 3</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 4</a>
                    </li>
                  </ul>
                </div>

                <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                  <h5 class="text-uppercase">Links</h5>

                  <ul class="list-unstyled mb-0">
                    <li>
                      <a class="text-body" href="#!">Link 1</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 2</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 3</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 4</a>
                    </li>
                  </ul>
                </div>

                <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                  <h5 class="text-uppercase">Links</h5>

                  <ul class="list-unstyled mb-0">
                    <li>
                      <a class="text-body" href="#!">Link 1</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 2</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 3</a>
                    </li>
                    <li>
                      <a class="text-body" href="#!">Link 4</a>
                    </li>
                  </ul> -->

      </div>
      <!--Grid column-->
      <!--Grid row-->
    </section>
    <!-- Section: Links -->
  </div>
  <!-- Grid container -->

  <!-- Copyright -->
  <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.05);" >
    © 2024 Copyright:
    <a class="text-reset fw-bold" href="https://github.com/CubeWhyMC">CubeWhy & muskf</a>
    LunarCN All rights reserved
  </div>
  <!-- Copyright -->
</footer>
<!-- Footer -->
<!-- MDB -->
<script type="text/javascript" src="/js/mdb.umd.min.js"></script>
<!-- i18n -->
<script src="/js/i18n.js"></script>
<script src="/js/download.js"></script>
<script src="/js/top.js"></script>
</body>
</html>
