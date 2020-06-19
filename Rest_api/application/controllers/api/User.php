<?php

defined('BASEPATH') or exit('No direct script access allowed');

// This can be removed if you use __autoload() in config.php OR use Modular Extensions
/** @noinspection PhpIncludeInspection */
require APPPATH . '/libraries/REST_Controller.php';

// use namespace
use Restserver\Libraries\REST_Controller;

/**
 * This is an example of a few basic user interaction methods you could use
 * all done with a hardcoded array
 *
 * @package         CodeIgniter
 * @subpackage      Rest Server
 * @category        Controller
 * @author          Phil Sturgeon, Chris Kacerguis
 * @license         MIT
 * @link            https://github.com/chriskacerguis/codeigniter-restserver
 */
class User extends REST_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('M_user');
    }

    public function register_post()
    {
        $query = $this->db->get("user");
        $field = $query->num_rows();
        $date = date('dm', time());
        $id_u = "ID-U" . $field . $date;
        $identity = $this->post('identity');
        $email = $this->post('email');
        $password = $this->post('password');
        $role = 2;
        $date_created = time();
        $registration = $this->M_user->register($id_u, $identity, $email, $password, $role, $date_created);

        if ($registration) {
            $this->response(array(
                'status' => $registration,
                'message' => 'Register Success'
            ), REST_Controller::HTTP_OK);
        } else {
            $this->response(array(
                'status' => $registration,
                'message' => 'Register Error'
            ), REST_Controller::HTTP_OK);
        }
    }

    public function login_post()
    {
        $email = $this->post('email');
        $password = $this->post('password');
        $login = $this->M_user->login($email, $password);

        if (!$login) {
            $this->response(array(
                'status' => false,
                'message' => 'Wrong Login Email or Password!'
            ), REST_Controller::HTTP_OK);
        } else {
            $this->response(array(
                'status' => true,
                'message' => 'Login Success!',
                'response' => $login
            ), REST_Controller::HTTP_OK);
        }
    }
}
